package com.account.Utility;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class AccountNumberGenerator {

	public String genarate() {
		return "ACC" + (1000000000L + new Random().nextInt(900000000));
		
	}
}
