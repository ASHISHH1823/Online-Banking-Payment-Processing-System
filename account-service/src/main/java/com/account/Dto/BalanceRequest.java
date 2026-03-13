package com.account.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BalanceRequest {
	
	@NotBlank
	private String accountNumber;
	@Positive
	private Double amount;

}
