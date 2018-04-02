package br.com.teltelecom.api.seguranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teltelecom.api.seguranca.entity.Usuario;
import br.com.teltelecom.api.seguranca.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repository;
	
	public Usuario salvar(Usuario usuario) {
		return repository.save(usuario); 
	}
	
	public void alterar(Usuario usuario) {
		repository.save(usuario);		
	}
	
	public void excluir(Long id) {
		repository.delete(id);
	}
	
	public List<Usuario> listar() {		
		return repository.findAll();
	}
	
	public Usuario pequisarId(Long id) {
		return repository.findOne(id);
	}

	public Usuario pequisarLogin(String login) {
		return repository.findByLogin(login);
	}
}
