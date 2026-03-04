package com.ashish.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
public class Registerrequest {
	
	@NotBlank
	private String username;
	@NotBlank
	private String password;
    private String role;

}
