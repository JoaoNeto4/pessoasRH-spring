package br.com.attornatus.resource;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.dto.PessoaEnderecoDTO;
import br.com.attornatus.event.RecursoCriadoEvent;
import br.com.attornatus.models.Pessoa;
import br.com.attornatus.repository.PessoaRepository;
import br.com.attornatus.repository.pessoa.PessoaIMPL;
import br.com.attornatus.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {


	@Autowired
    private PessoaService pessoaService;


	@Autowired
	private PessoaRepository pessoaRepository;


	@Autowired
	private ApplicationEventPublisher publisher;


	@GetMapping("/listar")
    public List<Pessoa> listarPessoas() {
        return pessoaService.listarPessoas();
    }


	@GetMapping("/{id}/endereco-principal")
	public PessoaEnderecoDTO buscarPessoaComEnderecoPrincipal(@PathVariable Long id) {
		Pessoa pessoa = pessoaService.buscarPessoaPorId(id);
		PessoaIMPL impl = new PessoaIMPL();
		return impl.pessoaToEnderecoDTO(pessoa);
	}

	
	@GetMapping("/aniversariantes")
    public ResponseEntity<List<Pessoa>> encontrarAniversariantes(
            @RequestParam("data") 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
		
        List<Pessoa> aniversariantes = pessoaService.encontrarAniversariantes(data);
        return ResponseEntity.ok(aniversariantes);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.buscarPessoaPorId(id);
        return ResponseEntity.ok(pessoa);
    }


    @GetMapping
	public Page<Pessoa> pesquisar(@RequestParam(required = false, defaultValue = "") String nome, Pageable pageable) {
		return pessoaRepository.findByNomeContaining(nome, pageable);
	}


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirPessoa(@PathVariable Long id) {
        pessoaService.excluirPessoa(id);
    }


    @PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaService.salvarPessoa(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}


    @PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.atualizarPessoa(codigo, pessoa);
		return ResponseEntity.ok(pessoaSalva);
	}


    @PutMapping("/{idPessoa}/principal/{idEndereco}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarEnderecoPrincipal(@PathVariable Long idPessoa, @PathVariable Long idEndereco) {
        pessoaService.atualizarEnderecoPrincipal(idPessoa, idEndereco);
    }


}
