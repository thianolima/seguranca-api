package br.com.teltelecom.api.seguranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teltelecom.api.seguranca.entity.Acao;
import br.com.teltelecom.api.seguranca.repository.AcaoRepository;

@Service
public class AcaoService {
	

	@Autowired
	AcaoRepository repository;	
	
	public Acao salvar(Acao acao) {
		return repository.save(acao); 
	}
	
	public void alterar(Acao acao) {
		repository.save(acao);		
	}
	
	public void excluir(Long id) {
		repository.delete(id);
	}
	
	public List<Acao> listar() {		
		return repository.findAll();
	}
	
	public Acao pequisarId(Long id) {
		return repository.findOne(id);
	}
}	
