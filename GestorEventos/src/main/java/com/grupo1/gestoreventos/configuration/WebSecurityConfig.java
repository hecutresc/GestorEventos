package com.grupo1.gestoreventos.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public static SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
				.requestMatchers("/", "/login", "/register/**", "/js/**", "/favicon/**","/media/**", "/vendor/**", "/css/**").permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/user/**")
				.hasRole("USER").anyRequest().authenticated().and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/userType").failureUrl("/login?error").permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true) // Invalida la sesión actual
	            .deleteCookies("JSESSIONID").permitAll()
				.and().exceptionHandling().accessDeniedPage("/errors/403");
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
