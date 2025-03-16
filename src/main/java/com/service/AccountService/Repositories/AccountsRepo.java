package com.service.AccountService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.AccountService.models.Account;
import com.service.AccountService.models.Customer;

@Repository
public interface AccountsRepo extends JpaRepository<Account, Long> {
	Account findByAccountId(String accountId);
}