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

import br.com.teltelecom.api.seguranca.entity.Usuario;
import br.com.teltelecom.api.seguranca.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService service;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_USUARIO_INSERIR')")
	public ResponseEntity<Usuario> inserir(@RequestBody Usuario usuario) {
		usuario = service.salvar(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario); 		 
	}
	
	@PutMapping
	@PreAuthorize("hasAuthority('ROLE_USUARIO_ALTERAR')")
	public ResponseEntity<Usuario> alterar(@RequestBody Usuario usuario) {
		usuario = service.salvar(usuario);
		return ResponseEntity.status(HttpStatus.OK).body(usuario); 	
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USUARIO_EXCLUIR')")
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_USUARIO_LISTAR')")
	public List<Usuario> listar() {		
		return service.listar();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USUARIO_PESQUISAR_ID')")
	public ResponseEntity<Usuario> pequisarId(@PathVariable Long id) {
		Usuario usuario = service.pequisarId(id);
		return usuario != null ? ResponseEntity.status(HttpStatus.OK).body(usuario) : ResponseEntity.noContent().build();
	}
}
