package com.service.AccountService.service;

import org.json.JSONObject;

public interface ICustomerAccountService {
	/**
	 * 
	 * @return if account was successfully added
	 */
	boolean addAccountToCustomer(Long customerId, double initialCredit);
	/**
	 * 
	 * @param customerId
	 * @return
	 */
	String getCustomerInfo(Long customerId);
}
