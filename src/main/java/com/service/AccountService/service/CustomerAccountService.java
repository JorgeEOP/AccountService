package com.service.AccountService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.AccountService.models.Account;
import com.service.AccountService.models.Customer;
import com.service.AccountService.repositories.CustomersRepo;
import com.service.AccountService.repositories.AccountsRepo;

@Service
public class CustomerAccountService implements ICustomerAccountService {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerAccountService.class);

	@Autowired
	private CustomersRepo customersRepo;
	@Autowired
	private AccountsRepo accountsRepo;
	
	@Override
	public boolean addAccountToCustomer(Long customerId, double initialCredit) {
		try {
			// Add the account to Accounts DB
			Account account = new Account(initialCredit);
			Customer customer = customersRepo.findByCustomerId(customerId);
			account.setCustomer(customer);
			accountsRepo.save(account);
			
			log.info("addAccountToCustomer::New Account (" + account.getAccountId() + ") Added to Customer (" + customer.getCustomerId() + ")");

			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public String getCustomerInfo(Long customerId) {
		String info = "";
		Customer customer = customersRepo.findByCustomerId(customerId);
		return info;
	}

	/**
	 * 
	 * @param customer
	 */
	public void addCustomer(Customer customer) {
		customersRepo.save(customer);
	}
	/**
	 * 
	 * @param account
	 */
	public void addAccount(Account account) {
		accountsRepo.save(account);
	}
}