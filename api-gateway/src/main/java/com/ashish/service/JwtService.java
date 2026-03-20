package com.ashish.service;

import com.ashish.exceptionHandler.InvalidTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtService {
	private final String SECRET="";

	public Claims validateToken(String token) {
		try {
		return Jwts.parser()
			.setSigningKey(SECRET)
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
