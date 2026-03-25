package com.ashish.service;

import org.springframework.stereotype.Service;

import com.ashish.exceptionHandler.InvalidTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
@Service
public class JwtService {
	private final String SECRET="my-super-secret-key-123456789012345";

	public Claims validateToken(String token) {
		try {
		return Jwts.parserBuilder()
			.setSigningKey(SECRET.getBytes())
			.build()
			.parseClaimsJws(token)
			.getBody();
		} catch (Exception e) {
			throw new InvalidTokenException("Invalid or Expired Token");
		}
	}

	public String extractRole(Claims claims) {
		
		return claims.get("role",String.class);
	}

}
