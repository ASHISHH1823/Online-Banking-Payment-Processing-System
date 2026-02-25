package com.ashish.service;


import com.ashish.entity.Appuser;

public interface JwtService {
	public String generateToken(Appuser appuser);

}
