package com.transaction.clien;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.transaction.dto.AccountResponse;


@FeignClient
public interface AccountClient {
	
	@GetMapping("/get/{accNo}")
	AccountResponse getDetails(@PathVariable String accNo);

}
