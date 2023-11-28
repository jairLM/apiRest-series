package com.company.backend.service;


import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.company.backend.model.User;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtBuilder.BuilderClaims;
import io.jsonwebtoken.JwtBuilder.BuilderHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	
	@Value("${security.jwt.expiration-minutes}")
	private Long EXPIRATION_MINUTES;
	
	@Value("${security.jwt.secret-key}")
	private String SECRET_KEY;
	
	

	public String generateToken(User user, Map<String, Object> extraClaims) {
		
		Date issuedAt = new Date(System.currentTimeMillis());
		Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000));
		BuilderHeader header = null;
		JwtBuilder headerBuilt = (JwtBuilder) header.add("typ", "JWT"); 
		
		String jwt = Jwts.builder()
				.claims(extraClaims)
				.subject(user.getUsername())
				.issuedAt(issuedAt)
				.signWith(generatedKey())
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.expiration(expiration)
				.compact();
		
		
		return jwt;
				
				
//			return	Jwts.builder()
//			
//			.setClaims(extraClaims)
//			.subject(user.getUsername())
//			.issuedAt(issuedAt)
//			.expiration(expiration)
//			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
//			.signWith(generatedKey(), SignatureAlgorithm.HS256)
//			.compact();
		
		
	}



	private Key generatedKey() {
		
		byte[] secretAsBytes = Decoders.BASE64.decode(SECRET_KEY);		
		
		return Keys.hmacShaKeyFor(secretAsBytes);
		
	}

}
