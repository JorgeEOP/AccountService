package com.service.AccountService.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.AccountService.models.Account;
import com.service.AccountService.models.Customer;
import com.service.AccountService.repositories.CustomersRepo;
import com.service.AccountService.response.ECustomerNotFound;
import com.service.AccountService.repositories.AccountsRepo;

@Service
public class CustomerAccountService implements ICustomerAccountService {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerAccountService.class);

	@Autowired
	private CustomersRepo customersRepo;
	@Autowired
	private AccountsRepo accountsRepo;
	@Autowired
	private TransactionService transactionService;  // This does should not be here!
	
	@Override
	public boolean addAccountToCustomer(Long customerId, double initialCredit) {
		try {
			// Add the account to Accounts DB
			Account account = new Account(initialCredit);
			Customer customer = customersRepo.findByCustomerId(customerId);
			account.setCustomer(customer);
			accountsRepo.save(account);

			transactionService.addInitialCredit(account);
			
			log.info("addAccountToCustomer::New Account (" + account.getAccountId() + ") Added to Customer (" + customer.getCustomerId() + ")");

			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public String getCustomerInfo(Long customerId) {
		
		Customer customer = customersRepo.findByCustomerId(customerId);
		
		if (customer != null) {
			List<Account> accounts = customer.getAccounts();
			
			JSONObject json = new JSONObject();
			json.put("Name", customer.getFirstName());
			json.put("Surname", customer.getLastName());

			ArrayList<Object> allAccounts = new ArrayList<Object>();
			
			for (int i = 0; i < accounts.size() ; ++i) {
				JSONObject accountInfo = new JSONObject();
				
				Account account = accounts.get(i);
				double balance = account.getCredit();
				Long transactions = transactionService.getTransactionsFromAccount(account);

				accountInfo.put("accountId", account.getAccountId());
				accountInfo.put("balance", balance);
			    accountInfo.put("transactions", transactions);
			    
			    allAccounts.add(accountInfo);
			}
			json.put("accounts", allAccounts);
			
			return json.toString();

		} else {
			throw new ECustomerNotFound("Customer with Id: " + customerId + " not found!");
		}
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