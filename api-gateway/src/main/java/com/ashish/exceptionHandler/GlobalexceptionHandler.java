package com.ashish.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalexceptionHandler {
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<?> handleUnauthorizedException(Exception e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
		
	}
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> handleAccessDeniedException(Exception e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
	}
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<?> handleInvalidTokenException(Exception e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
	}

}
