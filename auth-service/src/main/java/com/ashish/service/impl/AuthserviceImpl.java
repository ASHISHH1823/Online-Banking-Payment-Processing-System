package com.ashish.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ashish.dto.AuthResponse;
import com.ashish.dto.LoginRequest;
import com.ashish.dto.Registerrequest;
import com.ashish.entity.Appuser;
import com.ashish.exceptionHandler.UsernameAlredyExistException;
import com.ashish.repo.AuthRepo;
import com.ashish.security.CustomUserDetails;
import com.ashish.service.AuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthserviceImpl implements AuthService{
	
	private final AuthRepo authrepo;
	private final BCryptPasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	

	@Override
	public void register(Registerrequest req) throws Exception {
		if(authrepo.existsByUsername(req.getUsername())) {
			throw new UsernameAlredyExistException("User name Alredy exist!!");
		}
		Appuser appuser = Appuser.builder()
		.username(req.getUsername())
		.password(passwordEncoder.encode(req.getPassword()))
		.role("ROLE_USER")
		.build();
		authrepo.save(appuser);
		
	}

	@Override
	public AuthResponse login(LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
			
		if(authenticate.isAuthenticated()) {
			CustomUserDetails customUserDetails= (CustomUserDetails)authenticate.getPrincipal();
			
		}
		return null;
	}

}
