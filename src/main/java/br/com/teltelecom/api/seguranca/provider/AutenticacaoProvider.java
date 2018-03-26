package br.com.teltelecom.api.seguranca.provider;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.teltelecom.api.seguranca.entity.Usuario;
import br.com.teltelecom.api.seguranca.repository.UsuarioRepository;

@Component
public class AutenticacaoProvider implements AuthenticationProvider {

	@Autowired
	UsuarioRepository repository;
	
	@Value("${ldap.enabled}")
	private String ldapEnabled;
	
	@Value("${ldap.host}")
	private String ldapHost;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {		
	    String login = (String) authentication.getPrincipal();
	    String senha = (String) authentication.getCredentials();	        	    	
			    
	    Usuario usuario = new Usuario();
	    try {    
	    	
	    	if(Boolean.parseBoolean(ldapEnabled)){
		    	RestTemplate rest = new RestTemplate();
		    	
		    	String params = "{\"username\":\"" + login +"@grupotel.corp\",\"password\" : \"" + senha + "\"}";
		    	
		    	RequestEntity<String> reqAD = RequestEntity
		    			.post(URI.create(ldapHost)) 
		    			.body(params);
		    	
				ResponseEntity<String> respAD= rest.exchange(reqAD, String.class);
				
				if(respAD.getStatusCode() != HttpStatus.OK) {
					throw new AuthenticationCredentialsNotFoundException(respAD.getBody());
				}
				
				usuario = repository.findByLogin(login);
			
	    	} else {	    	
				usuario = repository.findByLoginAndSenha(login, senha);
			}
		    
		    if(usuario.getGrupo().getNome().isEmpty()) {
		        throw new AuthenticationCredentialsNotFoundException("Usuario ou Senha inválido !");
		    }	 
		    		    
		    UsernamePasswordAuthenticationToken retorno = new UsernamePasswordAuthenticationToken(
		    		usuario.getLogin(), usuario.getSenha(), usuario.getPermissoes());
		    
			return retorno;
		    
	    }catch(Exception e) {
	    	throw new AuthenticationCredentialsNotFoundException("Usuario ou Senha inválido !");
	    }	    
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
