package com.company.backend.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.company.backend.dao.UserRepository;
import com.company.backend.dto.AuthenticationRequest;
import com.company.backend.dto.AuthenticationResponse;
import com.company.backend.model.User;

@Service
public class AuthenticationService {

	private static final Logger log = LoggerFactory.getLogger(SerieServiceImpl.class); 
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public AuthenticationResponse login(AuthenticationRequest authRequest) {
		
		log.info("Iniciando login del token");
		
		
		UsernamePasswordAuthenticationToken authToken 
				= new UsernamePasswordAuthenticationToken(
				
				authRequest.getUsername(), authRequest.getPassword());
		
		authenticationManager.authenticate(authToken);
		
		User user = userRepository.findByUsername(authRequest.getUsername()).get();
		
		String jwt = jwtService.generateToken(user, generateExtraClaims(user));
			
		
		return new AuthenticationResponse(jwt);
	}

	private Map<String, Object> generateExtraClaims(User user) {
		Map<String, Object> extraClaims = new HashMap<String, Object>();
		extraClaims.put("name", user.getName());
		extraClaims.put("role", user.getAuthorities());
		return extraClaims;
	}

}
