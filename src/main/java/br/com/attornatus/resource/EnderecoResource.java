package br.com.attornatus.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.models.Endereco;
import br.com.attornatus.service.EnderecoService;

@RestController
@RequestMapping("/pessoas/{pessoaId}/enderecos")
public class EnderecoResource {


	@Autowired
    private EnderecoService enderecoService;


	@GetMapping
    public ResponseEntity<List<Endereco>> buscarEnderecosPorPessoa(@PathVariable Long pessoaId) {
        List<Endereco> enderecos = enderecoService.buscarEnderecosPorPessoa(pessoaId);
        return ResponseEntity.ok().body(enderecos);
    }


    @PostMapping
    public ResponseEntity<Endereco> salvar(@PathVariable Long pessoaId, @RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoService.salvar(pessoaId, endereco);
        return ResponseEntity.ok().body(novoEndereco);
    }


    @PutMapping("/{enderecoId}")
    public ResponseEntity<Endereco> atualizar(@PathVariable Long enderecoId, @RequestBody Endereco novoEndereco) {
        Endereco enderecoAtualizado = enderecoService.atualizar(enderecoId, novoEndereco);
        return ResponseEntity.ok().body(enderecoAtualizado);
    }


    @DeleteMapping("/{enderecoId}")
    public ResponseEntity<Void> excluir(@PathVariable Long enderecoId) {
        enderecoService.excluir(enderecoId);
        return ResponseEntity.noContent().build();
    }



}
