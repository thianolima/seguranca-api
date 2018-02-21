package br.com.teltelecom.api.seguranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teltelecom.api.seguranca.entity.Grupo;
import br.com.teltelecom.api.seguranca.repository.GrupoRepository;

@Service
public class GrupoService {

	@Autowired
	GrupoRepository repository;	
	
	public Grupo salvar(Grupo grupo) {
		return repository.save(grupo); 
	}
	
	public void alterar(Grupo grupo) {
		repository.save(grupo);		
	}
	
	public void excluir(Long id) {
		repository.delete(id);
	}
	
	public List<Grupo> listar() {		
		return repository.findAll();
	}
	
	public Grupo pequisarId(Long id) {
		return repository.findOne(id);
	}
}
