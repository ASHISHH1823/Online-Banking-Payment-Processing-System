package com.account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account.Dto.AccountRequest;
import com.account.Dto.AccountResponse;
import com.account.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/account")
public class AccountController {
	
	private final AccountService accountService;
	
	@PostMapping("/create")
	public ResponseEntity<AccountResponse> Create(@RequestBody AccountRequest accountRequest) throws Exception{
		AccountResponse accountResponse = accountService.create(accountRequest);
		return ResponseEntity.ok(accountResponse);	
	}
	
	

}
