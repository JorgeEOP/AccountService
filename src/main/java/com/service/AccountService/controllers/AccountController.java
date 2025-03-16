package com.service.AccountService.controllers;

import org.json.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.AccountService.models.Customer;

@RestController
public class AccountController {

	@PostMapping("/account/newAccount")
	public String openNewAccount(@RequestBody String customerInfo) {
		JSONTokener tokener = new JSONTokener(customerInfo);
		JSONObject json = null;
		try {
			json = new JSONObject(tokener);
			String data = "CustomerId: " + json.getString("customerId") + " ; InitialCredit: " + json.getString("initialCredit") + "\n";
			System.out.printf("AccountController::Request to open an Account: " + data);
			
			return "Account Opened\n";
		} catch (JSONException e) {
			e.printStackTrace();
			String error = "{\"Bad Request\": 400}";
			return error;
		}
	}

	@PostMapping("/account/getInfo")
	public String getInfo(@RequestBody Long customerId) {
		try {
			return "Account Opened\n";
		} catch (JSONException e) {
			e.printStackTrace();
			String error = "{\"Bad Request\": 400}";
			return error;
		}
	}
}
