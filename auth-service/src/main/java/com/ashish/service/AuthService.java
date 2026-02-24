package com.ashish.service;

import com.ashish.dto.AuthResponse;
import com.ashish.dto.LoginRequest;
import com.ashish.dto.Registerrequest;

public interface AuthService {

	void register(Registerrequest req) throws Exception;

	AuthResponse login(LoginRequest loginRequest);

}
