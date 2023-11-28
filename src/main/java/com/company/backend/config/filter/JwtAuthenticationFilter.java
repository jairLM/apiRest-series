package com.company.backend.config.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.company.backend.dao.UserRepository;
import com.company.backend.service.JwtService;
import com.company.backend.model.*;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{ //OncePerRequestFilter se segura que una petición sea procesada una sola vez

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//1.- Obtener el header que contiene el jwt
		String AuthHeader = request.getHeader("Authorization"); //Se llama Authorization y el formato es "Bearer (jwt)"
		
		//condición si el header llega vacio
		
		if (AuthHeader == null || !AuthHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return ;
			
		}
		
		
		//2.- Obtener el jwt desde el header
		String jwt = AuthHeader.split(" ")[1];
		
		//3.- Obtener subject/username desde el jwt
		String username = jwtService.extractUsername(jwt);
		
		//4.- Setear un objeto Authentication dentro del SecurityContext
		User user = userRepository.findByUsername(username).get();
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				username, null, user.getAuthorities() );
		
		SecurityContextHolder.getContext().setAuthentication(authToken);
		
		//5- Ejecutar el resto de filtros
		
		filterChain.doFilter(request, response);
		
	}

}
