package com.service.AccountService.controllers;

import org.json.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.AccountService.customers.Customer;

@RestController
public class AccountController {

	  //AccountController(EmployeeRepository repository) {
	  //AccountController() {
	    //this.repository = repository;
	  //}
	  
	 @PostMapping("/account/newAccount")
	 public String openNewAccount(@RequestBody String customerInfo) {
		 JSONTokener tokener = new JSONTokener(customerInfo);
		 JSONObject json = null;
		 try {
			 json = new JSONObject(tokener);
			 String data = "CustomerId: " + json.getString("customerId") + " ; InitialCredit: " + json.getString("initialCredit") + "\n";
			 System.out.printf("AccountController::Request to open an Account: " + data);
			 
			 return "Account Opened\n";
		 } catch(JSONException e) {
			 e.printStackTrace();
			 String error = "{\"Bad Request\": 400}";
			 return error;
		 }
	 }
	 /**
	  * Returns the current account Balance
	  * @param id
	  * @return
	 @GetMapping("/getBalance/{user}")
	 public String account(@PathVariable String id) {
	     String result = "" + id;
		 return result;
	  }
      */


	 /*
	  @PostMapping("/employees")
	  Employee newEmployee(@RequestBody Employee newEmployee) {
	    return repository.save(newEmployee);
	  }
	  */
	 
}
