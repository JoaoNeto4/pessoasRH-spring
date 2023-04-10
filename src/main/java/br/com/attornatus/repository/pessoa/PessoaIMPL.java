package br.com.attornatus.repository.pessoa;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.attornatus.dto.EnderecoDTO;
import br.com.attornatus.dto.PessoaEnderecoDTO;
import br.com.attornatus.models.Endereco;
import br.com.attornatus.models.Pessoa;


@Service
public class PessoaIMPL implements PessoaRepositoryQuery{


	@PersistenceContext
	private EntityManager manager;


	public PessoaEnderecoDTO pessoaToEnderecoDTO(Pessoa pessoa) {
		Optional.ofNullable(pessoa).orElseThrow(() -> new EmptyResultDataAccessException(1));
		Optional.ofNullable(pessoa.getEnderecoPrincipal()).orElseThrow(() -> new EmptyResultDataAccessException(1));

	    Endereco enderecoPrincipal = pessoa.getEnderecoPrincipal();
	    PessoaEnderecoDTO dto = new PessoaEnderecoDTO();

	    dto.setIdPessoa(pessoa.getId());
	    dto.setNome(pessoa.getNome());
	    dto.setDataNascimento(pessoa.getDataNascimento());
	    dto.setEndereco(new EnderecoDTO(
	    		enderecoPrincipal.getId(),
	    		enderecoPrincipal.getCidade(),
	    		enderecoPrincipal.getCep(),
	    		enderecoPrincipal.getNumero(),
	    		enderecoPrincipal.getLogradouro()
	    		));

	    return dto;
	}


	@Override
	public List<PessoaEnderecoDTO> totalPessoasAniversario(LocalDate data) {
	    String jpql = "SELECT new PessoaDTO(p, p.enderecoPrincipal) FROM Pessoa p WHERE MONTH(p.dataNascimento) = :mes AND DAY(p.dataNascimento) = :dia";
	    TypedQuery<PessoaEnderecoDTO> query = manager.createQuery(jpql, PessoaEnderecoDTO.class);
	    query.setParameter("mes", data.getMonthValue());
	    query.setParameter("dia", data.getDayOfMonth());
	    return query.getResultList();
	}
	
	


}
