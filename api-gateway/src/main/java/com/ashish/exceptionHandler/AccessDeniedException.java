package com.ashish.exceptionHandler;

public class AccessDeniedException extends RuntimeException{
	public AccessDeniedException(String message) {
		super(message);
	}

}
