package com.account.Dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BalanceResponse {
	private BigDecimal balance;

}
