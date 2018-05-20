package com.example.backoffice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/css/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.authorizeRequests()
					.antMatchers("/loginForm").permitAll()
					.antMatchers("/customers/create").hasAnyRole("SYSTEM", "ADMIN")
					.anyRequest().authenticated()
				.and()
				.formLogin()
					.loginProcessingUrl("/login")
					.loginPage("/loginForm")
					.failureUrl("/loginForm?error")
					.defaultSuccessUrl("/customers", true)
					.usernameParameter("username")
					.passwordParameter("password")
				.and()
				.logout()
					.logoutSuccessUrl("/loginForm");
		// @formatter:on
	}

	@Bean
	PasswordEncoder passwordEncoderEncoder() {
		return new Pbkdf2PasswordEncoder();
	}
}
