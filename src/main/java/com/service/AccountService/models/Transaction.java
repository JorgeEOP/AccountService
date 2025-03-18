package com.service.AccountService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Transaction {
	
	@Id @GeneratedValue
	private long transactionId;
	@OneToOne
	@JoinColumn(name = "account_id")
	private Account accountId;
	private Long numberOfTransactions;
	double transactionCredit;
	
	/**
	 * 
	 */
	public Transaction() {
		numberOfTransactions = Long.valueOf(0);
	}
	/**
	 * 
	 * @param account
	 */
	public Transaction(Account account) {
		accountId = account;
		transactionCredit = account.getCredit();
	}
	/**
	 * 
	 */
	public void addTransaction() {
		if (numberOfTransactions == null) {
			numberOfTransactions = Long.valueOf(1);
		} else {
			numberOfTransactions += 1;
		}
	}
	/**
	 * 
	 * @return
	 */
	public Long getNumberOfTransactions() {
		return numberOfTransactions;
	}
}
