package br.edu.utfpr.pb.ecommerce.Ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.edu.utfpr.pb.ecommerce.Ecommerce.service.impl.UsuarioServiceImpl;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			
			.exceptionHandling().accessDeniedPage("/403")
			.and().formLogin().loginPage("/login")
			.defaultSuccessUrl("/")
			.failureUrl("/login?error=bad_credentials").permitAll()
			.and().logout()
			.logoutSuccessUrl("/login")
			.and().authorizeRequests()
				.antMatchers("/categoria/**").hasAnyRole("ADMIN")
				.antMatchers("/compra/**").hasAnyRole("ADMIN")
				.antMatchers("/compraProduto/**").hasAnyRole("ADMIN")
				.antMatchers("/fornecedor/**").hasAnyRole("ADMIN")
				.antMatchers("/marca/**").hasAnyRole("ADMIN")
				.antMatchers("/produto/new").hasAnyRole("ADMIN")
				.antMatchers("/produto/ajax/**").hasAnyRole("ADMIN")
				.antMatchers("/produto/page").hasAnyRole("ADMIN")
				.antMatchers("/produto/imagem/").hasAnyRole("ADMIN", "USER")
				.antMatchers("/produto/pageComprar/").hasAnyRole("ADMIN", "USER")
				.antMatchers("/usuario/new").hasAnyRole("ADMIN")
				.antMatchers("/usuario/ajax/**").hasAnyRole("ADMIN")
				.antMatchers("/usuario/page").hasAnyRole("ADMIN")
				.antMatchers("/usuario/cadastro").permitAll()
				.antMatchers("/**").authenticated();		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/css/**")
			.antMatchers("/js/**")
			.antMatchers("/images/**")
			.antMatchers("/assets/**")
			.antMatchers("/webjars/**");
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return usuarioServiceImpl;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService())
			.passwordEncoder(passwordEncoder());
	}
}









