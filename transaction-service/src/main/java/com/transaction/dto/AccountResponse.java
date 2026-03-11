package com.transaction.dto;

import lombok.Data;

@Data
public class AccountResponse {
	private String holderName;
	private String accountNumber;
	private Double balance;

}
