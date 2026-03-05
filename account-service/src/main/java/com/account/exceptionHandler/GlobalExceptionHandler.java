package com.account.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AccountExistException.class)
	public ResponseEntity<?> handleAccountExistException(Exception e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}

}
