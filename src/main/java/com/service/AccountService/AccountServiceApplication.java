package com.service.AccountService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AccountServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}
}
