package com.service.AccountService.models;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;

@Entity
public class Account extends AccountBase {
	public Account() {}
	/**
	 * 
	 * @param initialCredit
	 */
	public Account(double initialCredit) {
		System.out.printf("Account::CreatingAnAccount with initial Credit: " + initialCredit);
		this.accountId = AccountBase.createNewAccountId();
		this.setInitialCredit(initialCredit);
	}
	/**
	 * 
	 * @param accountId
	 * @param initialCredit
	 */
	public Account(String accountId, double initialCredit) {
		this.setId(accountId);
		this.setInitialCredit(initialCredit);
	}
	/**
	 * 
	 * @param credit
	 * @return
	 */
	public boolean addCredit(long credit) {
		boolean addedCredit = false;
		return addedCredit;
	};
	/**
	 * 
	 * @return
	 */
	public boolean reduceCredit() {
		boolean reducedCredit = false;
		return reducedCredit;
	};
	
}