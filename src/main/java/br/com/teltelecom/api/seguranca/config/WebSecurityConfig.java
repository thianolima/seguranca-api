package br.com.teltelecom.api.seguranca.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.teltelecom.api.seguranca.filter.AutenticacaoFilter;
import br.com.teltelecom.api.seguranca.filter.TokenFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{	
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        	.csrf().disable().formLogin().loginProcessingUrl("/login")
        .and()
            .authorizeRequests().antMatchers("/login").permitAll()
        .and()
            .authorizeRequests().anyRequest().authenticated()
        .and()
            .addFilterBefore(new AutenticacaoFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new TokenFilter("/api/**", authenticationManager()), UsernamePasswordAuthenticationFilter.class);				
	}

}
