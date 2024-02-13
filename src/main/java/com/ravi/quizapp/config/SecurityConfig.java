package com.ravi.quizapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ravi.quizapp.filter.SecurityFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private InvalidUserAuthEntryPoint authenticationEntryPoint;
	
	@Autowired
	private SecurityFilter securityFilter;
	
	// For StateLess AUTH
	@Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Autowired
	public void configureUser(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	
	@Bean
	SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
		http.csrf((csrf)-> csrf.disable())
			.authorizeHttpRequests(request -> {
				try {
					request.requestMatchers(
							"/login",
							"/user/create",
							"/user/**",
							"/current-user",
							"/api/v1/auth/**",
							"/v2/api-docs",
							"/v3/api-docs",
							"/v3/api-docs/**",
							"/swagger-resources",
							"/swagger-resources/**",
							"/configuration/ui",
							"/configuration/security",
							"/webjars/**",
							"/swagger-ui.html",
							"/swagger-ui/**").permitAll()
						   .and()
						   .exceptionHandling(handling -> handling.authenticationEntryPoint(authenticationEntryPoint))
						   .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						   .and()
						   .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
		return http.build();
	}
	
}
