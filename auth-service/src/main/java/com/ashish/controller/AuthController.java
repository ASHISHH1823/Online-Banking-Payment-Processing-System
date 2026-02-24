package com.ashish.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.dto.AuthResponse;
import com.ashish.dto.LoginRequest;
import com.ashish.dto.Registerrequest;
import com.ashish.service.AuthService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Registerrequest req) throws Exception{
		authService.register(req);
		return ResponseEntity.ok("User register Sucessfully!!");	
	}
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
		AuthResponse authResponse=authService.login(loginRequest);
		if(ObjectUtils.isEmpty(authResponse)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.ok(authResponse);
		
	}

}
