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

import com.service.AccountService.loaders.CustomersLoader;
import com.service.AccountService.models.Customer;
import com.service.AccountService.repositories.CustomersRepo;
import com.service.AccountService.service.CustomerAccountService;

@RestController
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerAccountService customerAccountService;

	@PostMapping("/customer/newAccount")
	public String openNewAccount(@RequestBody String customerInfo) {
		JSONTokener tokener = new JSONTokener(customerInfo);
		JSONObject json = null;
		try {
			json = new JSONObject(tokener);
			Long customerId = json.getLong("customerId");
			Long initialCredit = json.getLong("initialCredit");

			log.info("[CustomerController]openNewAccount:: CustomerId: " + customerId);
			
			boolean accountAdded = customerAccountService.addAccountToCustomer(customerId, initialCredit);
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
			String info = customerAccountService.getCustomerInfo(customerId);
			return info;
		} catch (JSONException e) {
			e.printStackTrace();
			String error = "{\"Bad Request\": 400}";
			return error;
		}
	}
	
	@GetMapping("/customer/test")
	public String getTest() {
		try {
			return "Works!";
		} catch (JSONException e) {
			e.printStackTrace();
			String error = "{\"Bad Request\": 400}";
			return error;
		}
	}
}
