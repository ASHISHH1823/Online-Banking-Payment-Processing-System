package com.ashish.service;


import org.springframework.security.core.userdetails.UserDetails;

import com.ashish.entity.Appuser;

public interface JwtService {
	public String generateToken(Appuser appuser);
	public String extractUsername(String token);
	public boolean validateToken(String token,UserDetails userDetails);

}
