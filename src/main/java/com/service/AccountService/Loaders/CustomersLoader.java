package com.service.AccountService.loaders;

import com.service.AccountService.customers.Customer;
import com.service.AccountService.repositories.CustomersRepo;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomersLoader implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(CustomersLoader.class);
	private final CustomersRepo repository;
	String USERS_FILE_NAME = "static/UsersFakedataBase.txt";

	public CustomersLoader(CustomersRepo repository) {
		this.repository = repository;
	}

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
							repository.save(customer);
						}
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
		Set<String> accounts = new HashSet<String>();
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
		if (customerAsJson.has("customerId")) {
			firstName = customerAsJson.get("firstName").toString();
		}

		if (customerAsJson.has("accounts")) {
			JSONArray accountsArray = customerAsJson.getJSONArray(("accounts"));
			if (accountsArray != null) {
				if (accountsArray.length() != 0) {
					for (int i = 0; i < accountsArray.length(); ++i) {
						accounts.add(accountsArray.getString(i));
					}
				}
			}
		}

		if (customerAsJson.has("age")) {
			age = customerAsJson.getLong("age");
		} else {
			age = Long.valueOf(0);
		}

		Customer customer = new Customer(customerId, lastName, firstName, accounts, age);
		return customer;
	}
}