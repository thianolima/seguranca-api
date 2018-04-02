package br.com.teltelecom.api.seguranca.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.teltelecom.api.seguranca.entity.Usuario;
import br.com.teltelecom.api.seguranca.util.JwtUtil;

public class AutenticacaoFilter extends AbstractAuthenticationProcessingFilter {	
	
	private String senha; 
	
	public AutenticacaoFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
						
		Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha());
		this.senha = usuario.getSenha();
		Authentication auth = this.getAuthenticationManager().authenticate(token); 
		
		return auth;	
	}			
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
			FilterChain chain, Authentication auth) throws IOException, ServletException {
		
		String token = JwtUtil.getToken(auth.getName(), this.senha);						
		
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");

		// TODO:Preciso analisar porque esse objeto nao funciona
		//JSONObject jsonResponse = new JSONObject(token);			
		//jsonResponse.put("token", token);
			
		String json = "{\"token\":\""+token+"\"}";
		response.getWriter().write(json);
		response.setHeader(JwtUtil.HEADER_STRING, token);			
	}
}
