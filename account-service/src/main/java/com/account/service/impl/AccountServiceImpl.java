package com.account.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.account.Dto.AccountRequest;
import com.account.Dto.AccountResponse;
import com.account.entity.Account;
import com.account.exceptionHandler.AccountExistException;
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
	public AccountResponse create(AccountRequest accountRequest) throws Exception {
		if(accountRepo.existsByAccountNumber(accountRequest.getAccountNumber())) {
			throw new AccountExistException("Account already exists");
		}
		Account account = Account.builder()
		.accountNumber(accountRequest.getAccountNumber())
		.holderName(accountRequest.getHolderName())
		.balance(accountRequest.getBalance())
		.build();
		Account saveaccount = accountRepo.save(account);
		log.info("Account Created: {}",accountRequest.getAccountNumber());
		return mapper.map(saveaccount, AccountResponse.class);
	}

}
