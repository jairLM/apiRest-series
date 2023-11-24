package com.company.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.backend.dto.AuthenticationRequest;
import com.company.backend.dto.AuthenticationResponse;
import com.company.backend.service.AuthenticationService;
import com.company.backend.service.SerieServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(SerieServiceImpl.class); 
	
	@Autowired
	private AuthenticationService authenticationService;
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> login(
			@RequestBody AuthenticationRequest authRequest){
		
		log.info("Iniciando login");
		
		AuthenticationResponse jwtDto = authenticationService.login(authRequest);
		
		return ResponseEntity.ok(jwtDto);
	}
	
	
	
}
