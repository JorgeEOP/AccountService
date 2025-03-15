package com.service.AccountService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.AccountService.account.Account;
import com.service.AccountService.controllers.CustomerController;
import com.service.AccountService.customers.Customer;
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
	public boolean addAccountToCustomer(Long customerId, Long initialCredit) {
		boolean added = false;
		
		Account account = new Account();
		String accountId = account.getAccountId();
		accountsRepo.save(account);
		
		log.info("addAccountToCostumer:: customerId:ACCOUNT_ID: " + accountId + "\n");
		
		//AccountsRepo.save(null)
		
		Customer customer = customersRepo.findByCustomerId(customerId);
		boolean accountAdded = customer.addAccount(initialCredit);
		
		log.info("addAccountToCostumer:: customerId: " + customerId + " InitialCredit: " + initialCredit + "\n");
		
		return added;
	}
}