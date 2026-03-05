package com.account.exceptionHandler;

public class AccountExistException extends Exception{

	public AccountExistException(String message) {
		super(message);
	}
}
