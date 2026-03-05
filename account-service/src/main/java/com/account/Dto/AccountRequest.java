package com.account.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AccountRequest {	
	@NotBlank
	private String holderName;
	
	@NotBlank
	private String accountNumber;
	
	@NotBlank
	@Positive
	private Double balance;

}
