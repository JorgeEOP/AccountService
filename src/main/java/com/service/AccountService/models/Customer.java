package com.service.AccountService.models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Customer {

	@Id @GeneratedValue
	Long id;
	//private long customerUniqueId;
	private long customerId;
    
    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;
	private String firstName;
	private String lastName;
	private Long age;
	
	Customer() {}
	public Customer(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * 
	 * @param customerId
	 * @param lastName
	 * @param firstName
	 * @param accounts
	 * @param age
	 */
	public Customer(Long customerId, String lastName, String firstName, List<Account> accounts, Long age) {
		this.customerId = customerId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.age = age;
		this.accounts = accounts;
	}
	/**
	 * 
	 * @return
	 */
	public List<Account> getAccounts() {
		return this.accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	/**
	 * 
	 * @return
	 */
	public long getCustomerId() {
		return this.customerId;
	}
	/**
	 * 
	 * @return
	 */
	public String getFirstName() {
		return this.firstName;
	}
	/**
	 * 
	 * @return
	 */
	public String getLastName() {
		return this.lastName;
	}
	/**
	 * 
	 * @return
	 */
	public Long getAge() {
		return this.age;
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