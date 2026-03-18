package com.account.Dto;

import java.math.BigDecimal;

import com.account.enums.AccountStatus;

import lombok.Data;

@Data
public class AccountResponse {
	
	private String holderName;
	private String accountNumber;
	private BigDecimal balance;
	private AccountStatus isActive;

}
