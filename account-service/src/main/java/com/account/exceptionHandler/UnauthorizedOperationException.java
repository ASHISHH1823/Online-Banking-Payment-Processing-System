package com.account.exceptionHandler;

public class UnauthorizedOperationException extends RuntimeException{
	public UnauthorizedOperationException(String message) {
		super(message);
	}

}
