package com.service.AccountService.account;

/**
 * Base Account Class
 */
public abstract class AccountBase {

	protected Long customerID;
	protected float credit;
	
	public boolean addCredit(long credit) {
		boolean addedCredit = false;
		return addedCredit;
	};
	public boolean reduceCredit() {
		boolean reducedCredit = false;
		return reducedCredit;
	};
}