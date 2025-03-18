package com.service.AccountService.controllers;

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

import com.service.AccountService.response.AccountServiceResponse;
import com.service.AccountService.response.EInvalidParameters;
import com.service.AccountService.responseHandler.ResponseHandler;
import com.service.AccountService.service.CustomerAccountService;

@RestController
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerAccountService customerAccountService;
	/**
	 * 
	 * @param customerInfo
	 * @return
	 */
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
	/**
	 * 
	 * @param customerId
	 * @return
	 */
	@PostMapping("/customer/getInfo")
	public AccountServiceResponse getInfo(@RequestBody Long customerId) {
			String customerInfo = customerAccountService.getCustomerInfo(customerId);
		    return ResponseHandler.success(customerInfo, "Customer Information");
	}
}
