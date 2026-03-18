package com.account.Dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BalanceRequest {
	
	@NotBlank
	private String accountNumber;
	@Positive
	private BigDecimal amount;

}
