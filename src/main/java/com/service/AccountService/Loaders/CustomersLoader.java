package com.service.AccountService.loaders;

import com.service.AccountService.models.Account;
import com.service.AccountService.models.Customer;
import com.service.AccountService.repositories.AccountsRepo;
import com.service.AccountService.repositories.CustomersRepo;
import com.service.AccountService.service.CustomerAccountService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
/**
 * Class to read and store some initial Data from the resources.
 */
public class CustomersLoader implements CommandLineRunner {

	String USERS_FILE_NAME = "static/UsersFakedataBase.txt";
	private static final Logger log = LoggerFactory.getLogger(CustomersLoader.class);
	
	@Autowired
	private final CustomerAccountService customerAccountService;

	/**
	 * 
	 * @param customerAccountService
	 */
	public CustomersLoader(CustomerAccountService customerAccountService) {
		this.customerAccountService = customerAccountService;
	}
	/**
	 * 
	 */
	@Override
	public void run(String... args) {
		log.info("CustomersLoader:: LoadingData...");
		InputStream stream = this.getFileFromResourceAsStream();
		Scanner scanner = new Scanner(stream);

		if (stream != null) {
			try {
				while (scanner.hasNext() == true) {
					String line = scanner.nextLine();
					try {
						JSONObject customerJson = new JSONObject(line);
						Customer customer = this.getCustomerFromJson(customerJson);

						if (customer != null) {
							customerAccountService.addCustomer(customer);
						}
						if(customer.getAccounts().size() > 0) {
							this.addAccounts(customer.getAccounts());
						}

					    log.info("[Customer]:: New Customer Created with CustomerId: " + customer.getCustomerId());
					    log.info("[Customer]:: New Customer Created with LastName: " + customer.getLastName());
					    log.info("[Customer]:: New Customer Created with FirstName: " + customer.getFirstName());
					    log.info("[Customer]:: New Customer Created with Age: " + customer.getAge());
					    log.info("[Customer]:: New Customer Created with Accounts: " + customer.getAccounts());
					    
						log.info("------------------------------------------------------------------------------");
						
					} catch (Exception e) {
						log.error("" + e.toString());
					}
				}
				scanner.close();
				stream.close();
			} catch (IOException e) {
				log.error("Wrongline@" + USERS_FILE_NAME + "\n");
			}
		}
	}
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	private InputStream getFileFromResourceAsStream() {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(USERS_FILE_NAME);

		if (inputStream == null) {
			throw new IllegalArgumentException("File not found! " + USERS_FILE_NAME);
		} else {
			return inputStream;
		}
	}
	/**
	 * 
	 * @param customerAsString
	 * @return
	 */
	private Customer getCustomerFromJson(JSONObject customerAsJson) {
		Long customerId;
		String lastName = "";
		String firstName = "";
		List<Account> accounts = new ArrayList<Account>();
		Long age;

		if (customerAsJson.has("customerId")) {
			customerId = customerAsJson.getLong(("customerId"));
		} else {
			log.warn("Object does not contain CustomerId. Ignoring.");
			return null;
		}
		if (customerAsJson.has("lastName")) {
			lastName = customerAsJson.get("lastName").toString();
		}
		if (customerAsJson.has("firstName")) {
			firstName = customerAsJson.get("firstName").toString();
		}
		if (customerAsJson.has("age")) {
			age = customerAsJson.getLong("age");
		} else {
			age = Long.valueOf(0);
		}

		Customer customer = new Customer(customerId, lastName, firstName, accounts, age);

		if (customerAsJson.has("accounts")) {
			JSONArray accountsArray = customerAsJson.getJSONArray(("accounts"));
			if (accountsArray != null) {
				if (accountsArray.length() != 0) {
					for (int i = 0; i < accountsArray.length(); ++i) {
						String accountId = accountsArray.getString(i);
						Account account = new Account(accountId, this.createRandomNumber());
						account.setCustomer(customer);
						accounts.add(account);
					}
				}
			}
		}
		
		customer.setAccounts(accounts);

		return customer;
	}
	private void addAccounts(List<Account> accounts) {
		for (int i = 0 ; i < accounts.size() ; ++i) {
			customerAccountService.addAccount(accounts.get(i));
		}
	}
	/**
	 * 
	 * @return
	 */
	private double createRandomNumber() {
		Random r = new Random();
		double random = 0 + r.nextFloat() * (1000 - 0);
		double truncated = (int) (random * 100) / 100.0;
		return truncated; 
	}
}