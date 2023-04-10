package br.com.attornatus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.attornatus.models.Endereco;
import br.com.attornatus.models.Pessoa;
import br.com.attornatus.repository.EnderecoRepository;
import br.com.attornatus.repository.PessoaRepository;

@Service
public class EnderecoService {


	@Autowired
    private EnderecoRepository enderecoRepository;


    @Autowired
    private PessoaRepository pessoaRepository;


    public Endereco salvar(Long pessoaId, Endereco endereco) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() ->  new EmptyResultDataAccessException(1));
        endereco.setPessoa(pessoa);
        return enderecoRepository.save(endereco);
    }


    public List<Endereco> buscarEnderecosPorPessoa(Long pessoaId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() ->  new EmptyResultDataAccessException(1));
        return pessoa.getEndereco();
    }


    public Endereco buscarEnderecoPeloCodigo(Long codigo) {
    	Endereco enderecoSalvo =  enderecoRepository.findById(codigo)
								.orElseThrow(() -> new EmptyResultDataAccessException(1));

		return enderecoSalvo;
	}


    public Endereco atualizar(Long enderecoId, Endereco novoEndereco) {
        Endereco endereco = enderecoRepository.findById(enderecoId)
                .orElseThrow(() ->  new EmptyResultDataAccessException(1));
        endereco.setCep(novoEndereco.getCep());
        endereco.setCidade(novoEndereco.getCidade());
        endereco.setLogradouro(novoEndereco.getLogradouro());
        endereco.setNumero(novoEndereco.getNumero());
        return enderecoRepository.save(endereco);
    }


    public void excluir(Long enderecoId) {
        Endereco endereco = enderecoRepository.findById(enderecoId)
                .orElseThrow(() ->  new EmptyResultDataAccessException(1));
        enderecoRepository.delete(endereco);
    }


    public Pessoa buscarPessoaComEnderecoPrincipal(Long codigoPessoa) {
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(codigoPessoa);
        if (optionalPessoa.isPresent()) {
            Pessoa pessoa = optionalPessoa.get();
            pessoa.setEnderecoPrincipal(buscarEnderecoPrincipal(pessoa.getEndereco()));
            return pessoa;
        }
        return null;
    }


    private Endereco buscarEnderecoPrincipal(List<Endereco> enderecos) {
        for (Endereco endereco : enderecos) {
            if (endereco.equals(endereco.getPessoa().getEnderecoPrincipal())) {
                return endereco;
            }
        }
        return null;
    }


}
