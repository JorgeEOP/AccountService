package com.service.AccountService.account;

/**
 * Base Account Class
 */
public abstract class AccountBase {

	protected Long customerID;
	protected float credit;
	
	boolean addCredit() {
		boolean addedCredit = false;
		return addedCredit;
	};
	boolean reduceCredit() {
		boolean reducedCredit = false;
		return reducedCredit;
	};
}