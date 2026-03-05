package com.account.service;

import com.account.Dto.AccountRequest;
import com.account.Dto.AccountResponse;

public interface AccountService {

	AccountResponse create(AccountRequest accountRequest) throws Exception;

}
