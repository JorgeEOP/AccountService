package com.service.AccountService.customers;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import com.service.AccountService.account.Account;
import com.service.AccountService.service.CustomerAccountService;

@Entity
public class Customer {

	@Id @GeneratedValue
	Long id;
	private long customerId;
    //@OneToMany(mappedBy = "id")
	private Set<String> accounts;
	private String firstName;
	private String lastName;
	private Long age;
	private Account activeAccount;
	
	Customer() {}
	public Customer(String firstName) {
		this.firstName = firstName;
	}
	public Customer(Long customerId, String lastName, String firstName, Set<String> accounts, Long age) {
		this.customerId = customerId;
		this.lastName = firstName;
		this.firstName = firstName;
		this.age = age;
		this.accounts = accounts;
		
		System.out.printf("[Customer]:: New Customer Created with CustomerId: " + this.customerId + "\n");
		System.out.printf("[Customer]:: New Customer Created with LastName: " + this.lastName + "\n");
		System.out.printf("[Customer]:: New Customer Created with FirstName: " + this.firstName + "\n");
		System.out.printf("[Customer]:: New Customer Created with Age: " + this.age + "\n");
		System.out.printf("[Customer]:: --------------------------------------------------------------\n");
	}
	
	public boolean addAccount(Long initialCredit) {
		try {
			boolean accountAdded = false;
			String accountId = Account.createNewAccountId();
			//this.activeAccount = new Account(accountId);
			//System.out.printf("New Account" + accountId + "added to Customer: " + customerId + "\n");
			
			System.out.printf("New Account added. \n");
			return accountAdded;
		} catch (Exception e) {
			System.out.printf(e + "Customer::addAccount: failed to add an account\n");
			return false;
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