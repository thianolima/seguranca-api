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

import br.com.teltelecom.api.seguranca.entity.Grupo;
import br.com.teltelecom.api.seguranca.service.GrupoService;

@RestController
@RequestMapping("/api/grupo")
public class GrupoController {
	
	@Autowired
	GrupoService service;
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_GRUPO_INSERIR')")
	public ResponseEntity<Grupo> inserir(@RequestBody Grupo grupo) {
		grupo = service.salvar(grupo);
		return ResponseEntity.status(HttpStatus.CREATED).body(grupo); 		 
	}
	
	@PutMapping
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_GRUPO_ALTERAR')")
	public ResponseEntity<Grupo> alterar(@RequestBody Grupo grupo) {
		grupo = service.salvar(grupo);
		return ResponseEntity.status(HttpStatus.OK).body(grupo); 	
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_GRUPO_EXCLUIR')")
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_GRUPO_LISTAR')")
	public List<Grupo> listar() {		
		return service.listar();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_GRUPO_PESQUISAR_ID')")
	public ResponseEntity<Grupo> pequisarId(@PathVariable Long id) {
		Grupo grupo = service.pequisarId(id);
		return grupo != null ? ResponseEntity.status(HttpStatus.OK).body(grupo) : ResponseEntity.noContent().build();
	}
}
