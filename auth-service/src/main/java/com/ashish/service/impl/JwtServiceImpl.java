package com.ashish.service.impl;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ashish.entity.Appuser;
import com.ashish.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService{
	
	private final String SECRET="my-super-secret-key-123456789012345";
	
	

	@Override
	public String generateToken(Appuser appuser) {
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", appuser.getRole());
		
		String token = Jwts.builder().claims().add(claims)
		.subject(appuser.getUsername())
		.issuedAt(new Date(System.currentTimeMillis()))
		.expiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60))
		.and()
		.signWith(getKey())
		.compact();
		return token;
	}

	private Key getKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
		
	}

	@Override
	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}

	private Claims extractAllClaims(String token) {
		Claims claims=Jwts.parser()
				.verifyWith((javax.crypto.SecretKey)getKey())
		.build()
		.parseSignedClaims(token)
		.getPayload();
		return claims;
	}
	/*
	 * private SecretKey decryptKey(String secretKey2) { byte[] decode =
	 * Decoders.BASE64.decode(secretKey); return Keys.hmacShaKeyFor(decode); }
	 */

	@Override
	public boolean validateToken(String token, UserDetails userDetails) {
		String username = extractUsername(token);
		boolean isExpired=isTokenExpired(token);
		if(username.equalsIgnoreCase(userDetails.getUsername()) && !isExpired) {
			return true;
		}
		return false;
	}

	private boolean isTokenExpired(String token) {
		Claims allClaims = extractAllClaims(token);
		Date expirationDate = allClaims.getExpiration();
		return expirationDate.before(new Date());
	}

}
