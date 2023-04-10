package br.com.attornatus.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.attornatus.models.Endereco;
import br.com.attornatus.models.Pessoa;
import br.com.attornatus.repository.EnderecoRepository;
import br.com.attornatus.repository.PessoaRepository;



@Service
public class PessoaService {


	@Autowired
    private PessoaRepository pessoaRepository;


	@Autowired
	private EnderecoRepository enderecoRepository;


	@PersistenceContext
	private EntityManager manager;


	public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }


    public Pessoa buscarPessoaPorId(Long codigo) {
		Pessoa pessoaSalva =  pessoaRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));

		return pessoaSalva;
	}


    public Pessoa salvarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }


    public void excluirPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }


    public Pessoa atualizarPessoa(Long codigo, Pessoa pessoa) {

		Optional<Pessoa> pessoaPesquisada = pessoaRepository.findById(codigo);
		Pessoa pessoaSalva = pessoaPesquisada.orElseThrow(() -> new EmptyResultDataAccessException(1));

		pessoaSalva.getEndereco().clear();
		pessoaSalva.getEndereco().addAll(pessoa.getEndereco());
		pessoaSalva.getEndereco().forEach(c -> c.setPessoa(pessoaSalva));

		BeanUtils.copyProperties(pessoa, pessoaSalva, "id", "endereco", "enderecoPrincipal");

		return pessoaRepository.save(pessoaSalva);
	}


    public void adicionarEnderecoPrincipal(Long pessoaId, Long enderecoId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
            .orElseThrow(() -> new EmptyResultDataAccessException(1));
        Endereco endereco = enderecoRepository.findById(enderecoId)
            .orElseThrow(() -> new EmptyResultDataAccessException(1));
        pessoa.setEnderecoPrincipal(endereco);
        pessoaRepository.save(pessoa);
    }


    public Endereco buscarEnderecoPeloCodigo(Long codigo) {
    	Endereco enderecoSalvo =  enderecoRepository.findById(codigo)
								.orElseThrow(() -> new EmptyResultDataAccessException(1));

		return enderecoSalvo;
	}


    public void atualizarEnderecoPrincipal(Long idPessoa, Long idEndereco) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        Endereco endereco = enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        pessoa.setEnderecoPrincipal(endereco);
        pessoaRepository.save(pessoa);
    }


    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }
    
    /*aniversariantes*/
    public List<Pessoa> encontrarAniversariantes(LocalDate data) {
        List<Pessoa> todasAsPessoas = pessoaRepository.findAll();
        List<Pessoa> aniversariantes = new ArrayList<>();

        for (Pessoa pessoa : todasAsPessoas) {
            if (pessoa.getDataNascimento().getDayOfMonth() == data.getDayOfMonth() &&
                pessoa.getDataNascimento().getMonth() == data.getMonth()) {
                aniversariantes.add(pessoa);
            }
        }

        return aniversariantes;
    }


}


