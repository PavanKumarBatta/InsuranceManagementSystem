package com.cts.utility;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	
	private final String SECRET_KEY= "755e4400ca9b850a43dd026081259da308374e93f7e85a5b3d205036c0d9fd4c";
	
	private final Key key=Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	
	
	String generateToken(String userName,String role) {
		
		return Jwts.builder().setSubject(userName).claim("role",role)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	
	Claims extractClaims(String token) {
			
			return Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token)
					.getBody();
	}
	String extractUserName(String token) {
		
		return extractClaims(token).getSubject();
	}
	

	String extractRole(String token) {
		
		return extractClaims(token).get("role",String.class);
	}
	
	boolean isTokenValid(String token) {
		return !extractClaims(token).getExpiration().before(new Date());
	}
	
}
