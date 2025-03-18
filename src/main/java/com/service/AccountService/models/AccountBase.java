package com.service.AccountService.models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

/**
 * Base Account Class
 */
@MappedSuperclass
public abstract class AccountBase implements Serializable {

	@Id
	protected String accountId;
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	private double initialCredit;
	private double credit;

	/**
	 * 
	 * @param accountId
	 */
	public void setId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * 
	 * @return
	 */
	public String getAccountId() {
		return this.accountId;
	}
	/**
	 * 
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	/**
	 * 
	 * @param initialCredit
	 */
	public void setInitialCredit(double initialCredit) {
		this.initialCredit = initialCredit;
		this.credit = initialCredit;
	}
	/**
	 * 
	 * @return
	 */
	public double getInitialCredit() {
		return this.initialCredit;
	}
	/**
	 * 
	 * @return
	 */
	public double getCredit() {
		return this.credit;
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