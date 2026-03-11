package com.transaction.exception.holder;

public class InsufficientBalanceException extends RuntimeException{
	
	public InsufficientBalanceException(String message) {
		super(message);
	}

}
