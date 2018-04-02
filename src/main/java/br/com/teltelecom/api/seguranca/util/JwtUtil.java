package br.com.teltelecom.api.seguranca.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import br.com.teltelecom.api.seguranca.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	public static final String KEY_WORD = "Grupotel";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
	public static String getToken(String login, String senha) {
		
		Claims claims = Jwts.claims().setSubject(login);
		claims.put("data", new Date().toString());		
		claims.put("senha", senha);
		
		LocalDateTime ldt = LocalDateTime.now().plusHours(8);/*.plusSeconds(20);*/
	    Date dataExpiracao = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		
		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, KEY_WORD)
				.setExpiration(dataExpiracao)
				.compact();
	}
	
	public static Usuario validarToken(String token) {			
						
		try {
			Claims body = Jwts.parser()
							.setSigningKey(KEY_WORD)
							.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
							.getBody();
			
			Usuario usuario = new Usuario();			
			usuario.setLogin(body.getSubject());
			usuario.setSenha(body.get("senha").toString());
		
			return usuario;
		}catch(Exception e) {
			throw new RuntimeException("Token inválido, não foi possível realizar conversão");
		}	
	}
}
