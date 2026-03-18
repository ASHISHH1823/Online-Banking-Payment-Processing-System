package com.account.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.account.Dto.AccountRequest;
import com.account.Dto.AccountResponse;
import com.account.Dto.BalanceRequest;
import com.account.Dto.BalanceResponse;
import com.account.Utility.AccountNumberGenerator;
import com.account.entity.Account;
import com.account.enums.AccountStatus;
import com.account.exceptionHandler.AccountInactiveException;
import com.account.exceptionHandler.AccountNotFoundException;
import com.account.exceptionHandler.AccountalredyExistexception;
import com.account.exceptionHandler.InsufficientBalanceException;
import com.account.repo.AccountRepo;
import com.account.service.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountServiceImpl implements AccountService{

	private final AccountRepo accountRepo;
	private final AccountNumberGenerator accountNumberGenerator;
	private final ModelMapper mapper;
	
	@Override
	public AccountResponse create(AccountRequest request) {
		 String accountNumber = accountNumberGenerator.genarate();
		while(accountRepo.existsByAccountNumber(accountNumber)){
			accountNumber=accountNumberGenerator.genarate();
		}
		Account account = Account.builder()
		.accountNumber(accountNumber)
		.holderName(request.getHolderName())
		.isActive(AccountStatus.ACTIVE)
		.balance(BigDecimal.ZERO)
		.build();
		
		Account saveAccount = accountRepo.save(account);
		log.info("Account created: {}",accountNumber);
		
		return mapper.map(saveAccount, AccountResponse.class);
	}
    @Transactional(readOnly = true)
	@Override
	public AccountResponse getByAccountNumber(String accNo) {
		Account account = accountRepo.findByAccountNumber(accNo)
		.orElseThrow(()->new AccountNotFoundException("Account not found"));
		
		return mapper.map(account, AccountResponse.class);
	}

	@Override
	public Page<AccountResponse> getall(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,Sort.by("id"));
		Page<Account> page = accountRepo.findAll(pageable);
		return page.map(account->mapper.map(account, AccountResponse.class));
	}

	@Override
	public void inactive(String accNo) {
		Account account=accountRepo.findByAccountNumber(accNo)
		.orElseThrow(()-> new AccountNotFoundException("Account not found"));
		account.setIsActive(AccountStatus.INACTIVE);
		accountRepo.save(account);
		log.info("Account deactivated: {}",accNo);
		
	}

	@Override
	public AccountResponse getActiveAccount(String accNo) {
		Account account = accountRepo.findByAccountNumberAndIsActive(accNo,AccountStatus.ACTIVE)
				.orElseThrow(()->new AccountNotFoundException("Acctive Account not found"));
				;		
		return mapper.map(account, AccountResponse.class);
	}

	@Override
	public Page<AccountResponse> getallActiveAccounts(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,Sort.by("id"));
		Page<Account> page=accountRepo.findByIsActive(AccountStatus.ACTIVE,pageable);
		return page.map(account->mapper.map(account, AccountResponse.class));
	}
    @Transactional
	@Override
	public void deposit(BalanceRequest request) {
		Account account = accountRepo.findByAccountNumber
				(request.getAccountNumber()).orElseThrow(()->new AccountNotFoundException("Account not found"));
		
		if(account.getIsActive()==AccountStatus.INACTIVE) {
			throw new AccountInactiveException("Account is inactive. Deposit not allowed.");
		}
		if(request.getAmount().compareTo(BigDecimal.ZERO)<=0) {
			throw new IllegalArgumentException("Amount must be greater than zero");
		}
		account.setBalance(account.getBalance().add(request.getAmount()));
		accountRepo.save(account);
	}
    @Transactional
	@Override
	public void withdraw(BalanceRequest req) {
		Account account = accountRepo.findByAccountNumber(req.getAccountNumber())
		.orElseThrow(()->new AccountNotFoundException("Account not found"));
		
		if(account.getIsActive()==AccountStatus.INACTIVE) {
			throw new AccountInactiveException("Account is inactive. Withdrawal not allowed.");
		}
		
		if(account.getBalance().compareTo(req.getAmount())<0) {
			throw new InsufficientBalanceException("Insufficient balance");
		}
		account.setBalance(account.getBalance().subtract(req.getAmount()));
		accountRepo.save(account);
	}

	@Override
	public BalanceResponse getBalance(String accNo) {
		Account account = accountRepo.findByAccountNumber(accNo)
				.orElseThrow(()-> new AccountNotFoundException("Account not found"));
		return BalanceResponse.builder()
				.balance(account.getBalance())
				.build();
	}

	@Override
	public void activate(String accNo) {
		Account account = accountRepo.findByAccountNumber(accNo)
		.orElseThrow(()->new AccountNotFoundException("Account not found"));
		account.setIsActive(AccountStatus.ACTIVE);
		accountRepo.save(account);
		
	}

	@Override
	public void block(String accNo) {
		Account account = accountRepo.findByAccountNumber(accNo)
		.orElseThrow(()->new AccountNotFoundException("Account not found"));
		account.setIsActive(AccountStatus.BLOCKED);
		accountRepo.save(account);
		
	}
	

}
