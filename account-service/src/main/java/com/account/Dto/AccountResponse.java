package com.account.Dto;

import lombok.Data;

@Data
public class AccountResponse {
	
	private String holderName;
	private String accountNumber;
	private Double balance;

}
