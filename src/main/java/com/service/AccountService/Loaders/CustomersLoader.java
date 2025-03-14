package com.service.AccountService.Loaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.service.AccountService.Repositories.CustomersRepo;
import com.service.AccountService.customers.Customer;

@Component
public class CustomersLoader implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(CustomersLoader.class);
	private final CustomersRepo repository;
	
	public CustomersLoader(CustomersRepo repository) {
		this.repository = repository;
    }

	@Override
	public void run(String...args) {
		log.info("LoadingData....");
        //List<Image> images = List.of(new Image("Image 1"), new Image("Image 2"), new Image("Image 3"));
        
        repository.save(new Customer("Jorge"));
		
	}
}