package com.transaction.srvice.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.transaction.clien.AccountClient;
import com.transaction.dto.AccountResponse;
import com.transaction.dto.TransferRequest;
import com.transaction.repo.TransactionRepo;
import com.transaction.srvice.TransactionService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService{
	
	private final TransactionRepo trnsRepo;
	private final AccountClient accountClient;

	@Override
	public String transfer(TransferRequest transferRequest) {
		  
		AccountResponse sender = accountClient.getDetails(transferRequest.getFromAccount());
		AccountResponse receiver = accountClient.getDetails(transferRequest.getToAccount());
		
		if(sender.getBalance()<transferRequest.getAmount()) {
			throw new 
		}
		
		return null;
	}

	

}
