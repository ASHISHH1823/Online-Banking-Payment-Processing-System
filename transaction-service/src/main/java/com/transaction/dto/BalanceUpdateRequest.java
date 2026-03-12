package com.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceUpdateRequest {
	
	private String accountNumber;
	private Double amount;

}
