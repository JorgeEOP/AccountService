package com.service.AccountService.controllers;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.service.AccountService.models.Account;
import com.service.AccountService.response.AccountServiceResponse;
import com.service.AccountService.responseHandler.ResponseHandler;
import com.service.AccountService.service.CustomerAccountService;

@RestController
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerAccountService customerAccountService;
	
	private final DiscoveryClient discoveryClient;
	private final RestClient restClient;

	public CustomerController(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
		this.discoveryClient = discoveryClient;
		restClient = restClientBuilder.build();
	}
	/**
	 * 
	 * @return
	 */
	@PostMapping("/account/openAccount")
	public String openAccount(@RequestBody String customerInfo) {
		JSONTokener tokener = new JSONTokener(customerInfo);
		JSONObject json = null;

		json = new JSONObject(tokener);
		Long customerId = json.getLong("customerId");
		Long initialCredit = json.getLong("initialCredit");

		log.info("[CustomerController]openNewAccount:: CustomerId: " + customerId);

		//Add Account
		Account accountAdded = customerAccountService.addAccountToCustomer(customerId, initialCredit);
		
		//Add a Transaction
		ServiceInstance serviceInstance = discoveryClient.getInstances("Transactions-Service").get(0);
		JSONObject body = new JSONObject();
		//body.append("account", accountAdded);
		body.put("account", accountAdded);
		log.info("REQ_BOD" + body);
		String response = restClient.post()
				.uri(serviceInstance.getUri() + "/transaction/newTransaction")
				.contentType(MediaType.APPLICATION_JSON)
				.body(body)
				.retrieve()
				.body(String.class);
		
		log.info("TheRESPONSE: " + response);
		
		
		return response;
	}
	
	/**
	 * 
	 * @param customerInfo
	 * @return
	 */
	@PostMapping("/customer/newAccount")
	public AccountServiceResponse openNewAccount(@RequestBody String customerInfo) {
		JSONTokener tokener = new JSONTokener(customerInfo);
		JSONObject json = null;

		json = new JSONObject(tokener);
		Long customerId = json.getLong("customerId");
		Long initialCredit = json.getLong("initialCredit");

		log.info("[CustomerController]openNewAccount:: CustomerId: " + customerId);

		Account accountAdded = customerAccountService.addAccountToCustomer(customerId, initialCredit);
		
		return ResponseHandler.success(accountAdded, "Customer Information");
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
