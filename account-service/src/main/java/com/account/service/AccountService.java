package com.account.service;

import org.springframework.data.domain.Page;

import com.account.Dto.AccountRequest;
import com.account.Dto.AccountResponse;

public interface AccountService {

	AccountResponse create(AccountRequest request);

	AccountResponse getByAccountNumber(String accNo);

	Page<AccountResponse> getall(int pageNo, int pageSize);

	void inactive(String accNo);

	AccountResponse getActiveAccount(String accNo);

	Page<AccountResponse> getallActiveAccounts(int pageNo, int pageSize);

}
