package com.service.AccountService.response;

public class EInvalidParameters extends RuntimeException {
	public EInvalidParameters(String errorMessage) {
		super(errorMessage);
	}
}