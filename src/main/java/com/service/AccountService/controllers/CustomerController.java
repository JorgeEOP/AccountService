package com.service.AccountService.controllers;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.AccountService.customers.Customer;
import com.service.AccountService.loaders.CustomersLoader;
import com.service.AccountService.repositories.CustomersRepo;
import com.service.AccountService.service.CustomerAccountService;

@RestController
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerAccountService customerService;

	@PostMapping("/customer/newAccount")
	public String openNewAccount(@RequestBody String customerInfo) {
		JSONTokener tokener = new JSONTokener(customerInfo);
		JSONObject json = null;
		try {
			json = new JSONObject(tokener);
			//String data = "CustomerId: " + json.getString("customerId") + " ; InitialCredit: " + json.getString("initialCredit") + "\n";
			//log.info("openNewAccount::Request to open an Account: " + data);
			
			Long customerId = json.getLong("customerId");
			Long initialCredit = json.getLong("initialCredit");
			
			boolean accountAdded = customerService.addAccountToCustomer(customerId, initialCredit);
			
			if (accountAdded) {
        		return "Account Opened\n";
			}
			return "An Error Ocurred";

		} catch (JSONException e) {
			e.printStackTrace();
			String error = "{\"Bad Request\": 400}";
			return error;
		}
	}
	
	@PostMapping("/customer/getInfo")
	public String getInfo(@RequestBody Long customerId) {
		try {
			return "customerId";
		} catch (JSONException e) {
			e.printStackTrace();
			String error = "{\"Bad Request\": 400}";
			return error;
		}
	}
}
