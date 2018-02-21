package br.com.teltelecom.api.seguranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teltelecom.api.seguranca.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Usuario findByLoginAndSenha(String login, String senha);

	public Usuario findByLogin(String login);
}
