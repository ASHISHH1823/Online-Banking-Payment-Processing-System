package com.account.exceptionHandler;

public class AccountInactiveException extends RuntimeException{
	public AccountInactiveException(String message) {
		super(message);
	}

}
