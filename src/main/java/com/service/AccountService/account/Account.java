package com.service.AccountService.account;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;

@Entity
public class Account extends AccountBase {
	public Account() {
		System.out.printf("CreatingAnAccount");
		this.accountId = AccountBase.createNewAccountId();
	}
	/**
	 * 
	 * @param accountId
	 * @param initialCredit
	 */
	public Account(String accountId, Long initialCredit) {
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