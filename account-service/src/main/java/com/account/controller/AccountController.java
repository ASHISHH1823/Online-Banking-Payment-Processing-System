package com.account.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.account.Dto.AccountRequest;
import com.account.Dto.AccountResponse;
import com.account.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/account")
public class AccountController {
	
	public final AccountService accountService;
	
	@PostMapping("/create")
	public ResponseEntity<AccountResponse> Create(@RequestBody AccountRequest Request){
		AccountResponse accountResponse = accountService.create(Request);
		return ResponseEntity.ok(accountResponse);	
	}
	@GetMapping("/get/{accNo}")
	public ResponseEntity<AccountResponse> getDetails(@PathVariable String accNo){
		return ResponseEntity.ok(accountService.getByAccountNumber(accNo));
		
	}
	@GetMapping("/activeacc/{accNo}")
	public ResponseEntity<AccountResponse> getActiveAccount(@PathVariable String accNo){
		return ResponseEntity.ok(accountService.getActiveAccount(accNo));
		
	}
	@GetMapping("/getall")
	public ResponseEntity<Page<AccountResponse>> getAllAccounts(
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "5") int pageSize
			){
		
		Page<AccountResponse> accounts = accountService.getall(pageNo,pageSize);
		return ResponseEntity.ok(accounts);
		
	}
	@GetMapping("/getallactive")
	public ResponseEntity<Page<AccountResponse>> getAllActiveAccounts(
			@RequestParam(defaultValue = "0")int pageNo,
			@RequestParam(defaultValue = "5")int pageSize
			){
		Page<AccountResponse> accounts=accountService.getallActiveAccounts(pageNo,pageSize);
				return ResponseEntity.ok(accounts);
		
	}
	@DeleteMapping("/inactive/{accNo}")
	public ResponseEntity<String> InActive(@PathVariable String accNo){
		accountService.inactive(accNo);
		return ResponseEntity.ok("Account InActivated!!");
		
	}

}
