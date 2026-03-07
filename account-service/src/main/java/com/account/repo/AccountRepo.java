package com.account.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.account.Dto.AccountResponse;
import com.account.entity.Account;
import com.account.enums.AccountStatus;

public interface AccountRepo extends JpaRepository<Account, Long>{

	boolean existsByAccountNumber(String accountNumber);

	Optional<Account> findByAccountNumber(String accNo);

	Optional<Account> findByAccountNumberAndIsActive(String accNo, AccountStatus active);

	Page<Account> findByIsActive(AccountStatus active, Pageable pageable);
	

}
