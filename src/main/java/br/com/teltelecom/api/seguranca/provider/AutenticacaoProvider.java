package br.com.teltelecom.api.seguranca.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import br.com.teltelecom.api.seguranca.entity.Usuario;
import br.com.teltelecom.api.seguranca.repository.UsuarioRepository;

@Component
public class AutenticacaoProvider implements AuthenticationProvider {

	@Autowired
	UsuarioRepository repository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {		
	    String login = (String) authentication.getPrincipal();
	    String senha = (String) authentication.getCredentials();
		
	    Usuario usuario = new Usuario();
	    usuario = repository.findByLoginAndSenha(login, senha);
	    
	    try {
		    if(usuario.getGrupo().getNome().isEmpty()) {
		        throw new AuthenticationCredentialsNotFoundException("Usuario ou Senha inválido !");
		    }	 
	    }catch(Exception e) {
	    	throw new AuthenticationCredentialsNotFoundException("Usuario ou Senha inválido !");
	    }	    
	    	    
	    UsernamePasswordAuthenticationToken retorno = new UsernamePasswordAuthenticationToken(
	    		usuario.getLogin(), usuario.getSenha(), usuario.getPermissoes());
	    
		return retorno;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
