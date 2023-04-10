package br.com.attornatus.repository.pessoa;

import java.time.LocalDate;
import java.util.List;

import br.com.attornatus.dto.PessoaEnderecoDTO;


public interface PessoaRepositoryQuery {


	public List<PessoaEnderecoDTO> totalPessoasAniversario(LocalDate data);


}
