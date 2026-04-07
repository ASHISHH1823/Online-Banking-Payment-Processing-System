package com.ashish.service;

import java.security.Key;

import org.springframework.stereotype.Service;

import com.ashish.exceptionHandler.InvalidTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtService {
	private final String SECRET="my-super-secret-key-123456789012345";
	
	private Key getKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
		
	}

	public Claims validateToken(String token) {
		try {
		return Jwts.parserBuilder()
			.setSigningKey(getKey())
			.build()
			.parseClaimsJws(token)
			.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidTokenException("Invalid or Expired Token");
		}
	}

	public String extractRole(Claims claims) {
		
		return claims.get("role",String.class);
	}

}
