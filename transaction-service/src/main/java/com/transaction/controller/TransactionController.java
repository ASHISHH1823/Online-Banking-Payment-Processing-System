package com.transaction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.dto.TransferRequest;
import com.transaction.srvice.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaction")
public class TransactionController {
	
	private final TransactionService transactionService;
	
	@PostMapping("/transfer")
	public ResponseEntity<String> transfer(@RequestBody TransferRequest transferRequest){
		return ResponseEntity.ok(transactionService.transfer(transferRequest));
		
	}
	
	

}
