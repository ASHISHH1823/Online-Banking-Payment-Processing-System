package com.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class TransferRequest {
	
	@NotBlank
	private String fromAccount;
	
	@NotBlank
	private String toAccount;
	
	@NotNull
	@Positive
	private Double amount;

}
