package br.com.attornatus.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pessoa")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pessoa {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@NotEmpty
	private String nome;


	@NotNull
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;


	@JsonIgnoreProperties("pessoa")
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Endereco> endereco;


	@OneToOne
	@JsonIgnore
    @JoinColumn(name = "endereco_principal_id")
    private Endereco enderecoPrincipal;


}
