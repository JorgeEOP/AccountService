package com.service.AccountService.service;

public interface ICustomerAccountService {
	/**
	 * 
	 * @return if account was successfully added
	 */
	boolean addAccountToCustomer(Long customerId, Long initialCredit);
}
