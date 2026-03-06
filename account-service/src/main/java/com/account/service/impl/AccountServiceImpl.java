package com.account.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.account.Dto.AccountRequest;
import com.account.Dto.AccountResponse;
import com.account.entity.Account;
import com.account.exceptionHandler.AccountNotFoundException;
import com.account.exceptionHandler.AccountalredyExistexception;
import com.account.repo.AccountRepo;
import com.account.service.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountServiceImpl implements AccountService{

	private final AccountRepo accountRepo;
	private final ModelMapper mapper;
	
	@Override
	public AccountResponse create(AccountRequest request) {
		if(accountRepo.existsByAccountNumber(request.getAccountNumber())){
			throw new AccountalredyExistexception("Account already exists");
		}
		Account account = Account.builder()
		.accountNumber(request.getAccountNumber())
		.holderName(request.getHolderName())
		.balance(request.getBalance())
		.build();
		
		Account saveAccount = accountRepo.save(account);
		log.info("Account created: {}",request.getAccountNumber());
		
		return mapper.map(saveAccount, AccountResponse.class);
	}

	@Override
	public AccountResponse getByAccountNumber(String accNo) {
		Account account = accountRepo.findByAccountNumber(accNo)
		.orElseThrow(()->new AccountNotFoundException(accNo));
		
		return mapper.map(account, AccountResponse.class);
	}

}
