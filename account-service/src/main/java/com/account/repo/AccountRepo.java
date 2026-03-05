package com.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.account.Dto.AccountRequest;
import com.account.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Long>{


	boolean existsByAccountNumber(String accountNumber);

}