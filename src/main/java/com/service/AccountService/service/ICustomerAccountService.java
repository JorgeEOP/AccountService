package com.service.AccountService.service;

import com.service.AccountService.models.Account;

public interface ICustomerAccountService {
	/**
	 * 
	 * @return if account was successfully added
	 */
	Account addAccountToCustomer(Long customerId, double initialCredit);
	/**
	 * 
	 * @param customerId
	 * @return
	 */
	String getCustomerInfo(Long customerId);
}
