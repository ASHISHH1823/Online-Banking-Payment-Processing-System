package com.account.Utility;

import org.springframework.stereotype.Component;

import com.account.exceptionHandler.UnauthorizedOperationException;

@Component
public class Validations {
	public void validateAdmin(String role) {
		if(!"ROLE_ADMIN".equals(role)) {
			throw new UnauthorizedOperationException("Only ADMIN can perform this operation");
		}
	}

}
