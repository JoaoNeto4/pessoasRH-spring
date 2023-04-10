package br.com.attornatus.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaEnderecoDTO {


	private Long idPessoa;

    private String nome;

    private LocalDate dataNascimento;

    private EnderecoDTO endereco;


}
