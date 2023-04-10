package br.com.attornatus.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {


	@Id
	private Long id;

	private String cidade;

	private String cep;

	private Integer numero;

	private String logradouro;


}
