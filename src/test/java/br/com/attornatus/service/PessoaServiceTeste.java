package br.com.attornatus.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import br.com.attornatus.models.Endereco;
import br.com.attornatus.models.Pessoa;
import br.com.attornatus.repository.EnderecoRepository;
import br.com.attornatus.repository.PessoaRepository;


@ExtendWith(MockitoExtension.class)
public class PessoaServiceTeste { //extends AplicationConfigTest{


	@Mock
    private EnderecoRepository enderecoRepository;


	@Mock
    private PessoaRepository pessoaRepository;


	@InjectMocks
	private PessoaService pessoaService;


	private Pessoa pessoa;
	private List<Endereco> enderecos;


    @BeforeEach
    void setUp() {
        pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setDataNascimento(LocalDate.parse("2023-03-03"));
        pessoa.setNome("João");

        Endereco endereco1 = new Endereco();
        endereco1.setId(1L);
        endereco1.setCidade("Palmas");
        endereco1.setNumero(123);

        Endereco endereco2 = new Endereco();
        endereco2.setId(2L);
        endereco2.setCidade("Curitiba");
        endereco2.setNumero(456);

        enderecos = new ArrayList<>();
        enderecos.add(endereco1);
        enderecos.add(endereco2);

        pessoa.setEndereco(enderecos);
    }


    @Test
    void listarPessoas_DeveRetornarListaDePessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(pessoa);

        when(pessoaRepository.findAll()).thenReturn(pessoas);

        List<Pessoa> result = pessoaService.listarPessoas();

        Assertions.assertEquals(pessoas, result);
    }


    @Test
    void buscarPessoaPorIdDeveRetornarPessoaQuandoEncontrar() {
        when(pessoaRepository.findById(pessoa.getId())).thenReturn(Optional.of(pessoa));

        Pessoa result = pessoaService.buscarPessoaPorId(pessoa.getId());

        Assertions.assertEquals(pessoa, result);
    }


    @Test
    void buscarPessoaPorIdDeveLancarExceptionQuandoNaoEncontrarPessoa() {
        when(pessoaRepository.findById(pessoa.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            pessoaService.buscarPessoaPorId(pessoa.getId());
        });
    }


    @Test
    void salvarPessoaDeveRetornarPessoaSalva() {
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        Pessoa result = pessoaService.salvarPessoa(pessoa);

        Assertions.assertEquals(pessoa, result);
    }


    @Test
    void excluirPessoaDeveChamarRepositoryComIdDaPessoa() {
        pessoaService.excluirPessoa(pessoa.getId());

        verify(pessoaRepository).deleteById(pessoa.getId());
    }


    @Test
    public void atualizarPessoaDeveAtualizarPessoaComEnderecos() {
        // given
        Long codigo = 1L;
        Pessoa pessoa = new Pessoa(1L, "João", LocalDate.parse("2023-03-03"), new ArrayList<Endereco>(), null);
        Endereco endereco1 = new Endereco(1L, "Palmas", "85555000", 123, "Rua dos Testes", pessoa);
        Endereco endereco2 = new Endereco(2L, "Curitiba", "555000888", 100, "Rua dos Pardais", pessoa);
        pessoa.setId(codigo);
        pessoa.setNome("João");
        pessoa.setDataNascimento(LocalDate.parse("2023-03-03"));
        pessoa.setEnderecoPrincipal(endereco1);
        pessoa.setEndereco(Arrays.asList(endereco1, endereco2));

        Pessoa pessoaAtualizada = new Pessoa();
        pessoaAtualizada.setNome("João Silva");
        pessoaAtualizada.setDataNascimento(LocalDate.parse("2023-03-04"));
        pessoaAtualizada.setEnderecoPrincipal(endereco2);
        pessoaAtualizada.setEndereco(Arrays.asList(endereco2));

        when(pessoaRepository.findById(codigo)).thenReturn(Optional.of(pessoa));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaAtualizada);

        // when
        Pessoa pessoaSalva = pessoaService.atualizarPessoa(codigo, pessoaAtualizada);

        // then
        Assertions.assertEquals(pessoaAtualizada.getNome(), pessoaSalva.getNome());
        Assertions.assertEquals(pessoaAtualizada.getEnderecoPrincipal(), pessoaSalva.getEnderecoPrincipal());
        Assertions.assertEquals(pessoaAtualizada.getEndereco(), pessoaSalva.getEndereco());

        verify(pessoaRepository).findById(codigo);
        verify(pessoaRepository).save(pessoaAtualizada);
        verifyNoMoreInteractions(pessoaRepository);
    }



}
