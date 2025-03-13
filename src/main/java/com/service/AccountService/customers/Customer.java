package com.service.AccountService.customers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.service.AccountService.account.Account;

public class Customer {
	private long customerId;
	Long id;
	private Map<String, Account> accounts;
	Customer() {
		String idAsString = Customer.createCustomerId();
		this.id = Long.valueOf(idAsString);
		System.out.printf("New Customer Created with Id: " + idAsString);
	}
	/**
	 * 
	 */
	public void addAccount() {
		try {
			Account newAccount = new Account();
			String accountId = Account.createNewAccountId();

			if (this.accounts == null) {
				this.accounts = new HashMap<>();
			}

			if (this.accounts.containsKey(accountId) == false) {
				this.accounts.put(accountId, newAccount);
			}
			
			System.out.printf("New Account added.");
		} catch (Exception e) {
			System.out.printf(e + "Customer::addAccount: failed to add an account");
		}
	}

	public void removeAccount(Long accountId) {
	}
	
	public static String createCustomerId() {
		Date now = new Date();
		UUID uuid = UUID.randomUUID();
		long nowInMs = now.getTime();
		String nowAsString = String.valueOf(nowInMs);
		String UUIDAsString = String.valueOf(nowInMs);
		
		String idAsString = nowAsString + UUIDAsString;
		return idAsString;
	}
}