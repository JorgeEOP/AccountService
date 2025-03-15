package com.service.AccountService.account;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Base Account Class
 */
@MappedSuperclass
public abstract class AccountBase implements Serializable {

	@Id
	protected String accountId;
	protected float credit;
	protected Long customerId;
	protected Long initialCredit;
	/**
	 * 
	 */
	public void setId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountId() {
		return this.accountId;
	}
	/**
	 * 
	 * @param initialCredit
	 */
	public void setInitialCredit(Long initialCredit) {
		this.initialCredit = initialCredit;
		this.credit = initialCredit;
	}
	/**
	 * 
	 * @return
	 */
	public static String createNewAccountId() {
		Date now = new Date();
		long nowInMs = now.getTime();
		String nowAsString = String.valueOf(nowInMs);
		String accountId = "K" + nowAsString;
		return accountId;
	}
}