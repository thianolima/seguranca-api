package br.com.teltelecom.api.seguranca.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.teltelecom.api.seguranca.entity.Usuario;
import br.com.teltelecom.api.seguranca.util.JwtUtil;

public class TokenFilter extends AbstractAuthenticationProcessingFilter {

	public TokenFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		String header = request.getHeader(JwtUtil.HEADER_STRING);
		
	    if (header != null) {
	    	
	    	String token = header.replace(JwtUtil.TOKEN_PREFIX, "");	    
	    	Usuario usuario = new Usuario();
	    	usuario = JwtUtil.validarToken(token);
	    
	    	if(!usuario.getLogin().isEmpty()){
	    		return getAuthenticationManager().authenticate(
	    				new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha()));
	    	}
	    }
	    
	    throw new AuthenticationCredentialsNotFoundException("JWT Token Not valid");
	}	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
			FilterChain chain, Authentication authResult) throws IOException, ServletException {
	    
		 SecurityContext context = SecurityContextHolder.createEmptyContext();
	     context.setAuthentication(authResult);
	     SecurityContextHolder.setContext(context);
	     chain.doFilter(request, response);
	  }
}
