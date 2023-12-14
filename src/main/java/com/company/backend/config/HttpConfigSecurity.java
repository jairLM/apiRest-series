package com.company.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.company.backend.config.filter.JwtAuthenticationFilter;
import com.company.backend.util.Permission;

@Configuration
public class HttpConfigSecurity {

	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
		
			.csrf(csrfConfigurer -> csrfConfigurer.disable())
			.sessionManagement(sessionManConfig -> sessionManConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authenticationProvider)
			.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.authorizeHttpRequests(authConfig -> {
				
				authConfig					
					
					.requestMatchers(HttpMethod.PUT, "/v1/series/**").permitAll()
					.requestMatchers(HttpMethod.DELETE, "/v1/series/**").permitAll()
					.requestMatchers(HttpMethod.POST,"/auth/**").permitAll();
				
				authConfig.requestMatchers(HttpMethod.POST, "/v1/series").hasAuthority(Permission.SAVE_ONE_SERIE.name());
				authConfig.requestMatchers(HttpMethod.GET, "/v1/series").permitAll();
					;
				
			});
		
		
		
		return http.build();
		
		
	}
	
	
	
}
