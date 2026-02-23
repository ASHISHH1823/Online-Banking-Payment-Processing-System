package com.ashish.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
public class Registerrequest {
	
	public String username;
	public String password;
	public String role;

}
