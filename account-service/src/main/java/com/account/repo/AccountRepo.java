package com.account.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.account.Dto.AccountResponse;
import com.account.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Long>{

	boolean existsByAccountNumber(String accountNumber);

	Optional<Account> findByAccountNumber(String accNo);

}
