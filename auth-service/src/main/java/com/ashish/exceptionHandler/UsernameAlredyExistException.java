package com.ashish.exceptionHandler;

public class UsernameAlredyExistException extends Exception{

	public UsernameAlredyExistException(String message) {
		super(message);
	}
}
