package com.transaction.exception.holder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<Map<String, Object>> handleInsufficientBalanceException(Exception e){
		Map<String, Object> error = new HashMap<>();
		error.put("Message", e.getMessage());
		error.put("timesTamp", LocalDateTime.now());
		error.put("Status", HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);	
	}

}
