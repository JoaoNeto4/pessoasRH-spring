package br.com.attornatus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.attornatus.models.Endereco;
import br.com.attornatus.models.Pessoa;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {


	public Page<Endereco> findByPessoaContaining(Pessoa pessoa, Pageable pageable);


}
