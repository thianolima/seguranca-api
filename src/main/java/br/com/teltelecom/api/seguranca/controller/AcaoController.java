package br.com.teltelecom.api.seguranca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teltelecom.api.seguranca.entity.Acao;
import br.com.teltelecom.api.seguranca.service.AcaoService;

@RestController
@RequestMapping("/api/acao")
public class AcaoController {

	@Autowired
	AcaoService service;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ACAO_INSERIR')")
	public ResponseEntity<Acao> inserir(@RequestBody Acao acao) {
		acao = service.salvar(acao);
		return ResponseEntity.status(HttpStatus.CREATED).body(acao); 		 
	}
	
	@PutMapping
	@PreAuthorize("hasAuthority('ROLE_ACAO_ALTERAR')")
	public ResponseEntity<Acao> alterar(@RequestBody Acao acao) {
		acao = service.salvar(acao);
		return ResponseEntity.status(HttpStatus.OK).body(acao); 	
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ACAO_EXCLUIR')")
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ACAO_LISTAR')")
	public List<Acao> listar() {		
		return service.listar();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ACAO_PESQUISAR_ID')")
	public ResponseEntity<Acao> pequisarId(@PathVariable Long id) {
		Acao acao = service.pequisarId(id);
		return acao != null ? ResponseEntity.status(HttpStatus.OK).body(acao) : ResponseEntity.noContent().build();
	}
	
}
