package com.service.AccountService.customers;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import com.service.AccountService.account.Account;

@Entity
public class Customer {
	
	@Id @GeneratedValue
	Long id;
	private long customerId;
	private Set<String> accounts;
	private String firstName;
	private String lastName;
	private Long age;
	//private Account activeAccount;
	
	public Customer(String firstName) {
		//long customerId = Customer.createCustomerId();
		//this.id = customerId;
		this.firstName = firstName;
		System.out.printf("New Customer Created with Id: " + this.id + "\n");
		System.out.printf("New Customer Created with Name: " + this.firstName + "\n");
		
		//System.out.printf("New Customer Created with Id: " + customerId + "\n");
	}
	public Customer(Long customerId, String lastName, String firstName, Set<String> accounts, Long age) {
		this.customerId = customerId;
		this.lastName = firstName;
		this.firstName = firstName;
		this.age = age;
		this.accounts = accounts;
		
		System.out.printf("New Customer Created with CustomerId: " + this.customerId + "\n");
		System.out.printf("New Customer Created with LastName: " + this.lastName + "\n");
		System.out.printf("New Customer Created with FirstName: " + this.firstName + "\n");
		System.out.printf("New Customer Created with Age: " + this.age + "\n");
	}
	
	/**
	 * 
	 */
	public void addAccount() {
		try {
			String accountId = Account.createNewAccountId();

			if (this.accounts == null) {
				this.accounts = new HashSet<String>();
			}
			
			if(this.accounts.contains(accountId) == false) {
				this.accounts.add(accountId);
			}
			//this.activeAccount = new Account();
			
			System.out.printf("New Account added.");
		} catch (Exception e) {
			System.out.printf(e + "Customer::addAccount: failed to add an account");
		}
	}
	public void addAccount(long initialCredit) {
		try {
			String accountId = Account.createNewAccountId();

			if (this.accounts == null) {
				this.accounts = new HashSet<String>();
			}
			
			if(this.accounts.contains(accountId) == false) {
				this.accounts.add(accountId);
			}
			
			//this.activeAccount = new Account();
			
			//if (initialCredit > 0) {
			//	this.activeAccount.addCredit(initialCredit);
			//}
			
			System.out.printf("New Account added.");
		} catch (Exception e) {
			System.out.printf(e + "Customer::addAccount: failed to add an account");
		}
	}

	/**
	 * 
	 * @param accountId
	 */
	public void removeAccount(Long accountId) {
	}
	/**
	 * 
	 * @return
	 */
	public static long createCustomerId() {
		Date now = new Date();
		long nowInMs = now.getTime();
		
		return nowInMs;
	}
}