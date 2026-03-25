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
	
	private String secretKey="my-super-secret-key-123456789012345";
	
	

	public JwtServiceImpl() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = keyGen.generateKey();
			secretKey=Base64.getEncoder().encodeToString(sk.getEncoded());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String generateToken(Appuser appuser) {
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", appuser.getRole());
		
		String token = Jwts.builder().claims().add(claims)
		.subject(appuser.getUsername())
		.issuedAt(new Date(System.currentTimeMillis()))
		.expiration(new Date(System.currentTimeMillis()+60 * 60 * 60 * 10))
		.and()
		.signWith(getKey())
		.compact();
		return token;
	}

	private Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	@Override
	public String extractUsername(String token) {
		Claims claims=extractAllClaims(token);
		return claims.getSubject();
	}

	private Claims extractAllClaims(String token) {
		Claims claims=Jwts.parser().verifyWith(decryptKey(secretKey))
		.build()
		.parseSignedClaims(token)
		.getPayload();
		return claims;
	}

	private SecretKey decryptKey(String secretKey2) {
		byte[] decode = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(decode);
	}

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
