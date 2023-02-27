package com.grupo1.gestoreventos.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		//Indicamos la rutas a las cuales todos tendrán acceso y a las que hay que estar registrado para acceder
		http.authorizeHttpRequests().requestMatchers("/","/register","/register/**","/login","/login/**","/css/**","/js/**","/media/**").permitAll()
		.requestMatchers("/admin","/admin/**").hasRole("ADMIN")
		.requestMatchers("/gestor", "/gestor/**").hasAnyRole("ADMIN","USER")
		.and().formLogin().loginPage("/login").and().logout().permitAll().logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/errors/403");
		return http.build();
		
	}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder build) throws Exception{
		build.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
