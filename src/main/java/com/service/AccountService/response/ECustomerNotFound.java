package com.service.AccountService.response;

public class ECustomerNotFound extends RuntimeException {
	public ECustomerNotFound(String errorMessage) {
		super(errorMessage);
	}
}