package com.ashish.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
	
	public String token;

}
