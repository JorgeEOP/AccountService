package com.service.AccountService.loaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.service.AccountService.account.Account;
import com.service.AccountService.repositories.AccountsRepo;

@Component
public class AccountsLoader implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(CustomersLoader.class);
	private final AccountsRepo repository;
	/**
	 * 
	 * @param repository
	 */
	public AccountsLoader(AccountsRepo repository) {
		this.repository = repository;
	}
	/**
	 * 
	 */
	@Override
	public void run(String... args) {
		//log.info("AccountsLoader:: LoadingData...");
	}
	/**
	 * Just for the demo. we have some accounts in txt
	 * @param accountId
	 */
	public void addAccount(String accountId) {
		log.info("AccountLoader::addAccount");
		Account account = new Account();
		repository.save(account);
	}
	/**
	 * 
	 * @param accountId
	 * @param initialCredit
	 */
	public void addAccount(String accountId, Long initialCredit) {
		Account account = new Account(accountId, initialCredit);
		repository.save(account);
	}

}