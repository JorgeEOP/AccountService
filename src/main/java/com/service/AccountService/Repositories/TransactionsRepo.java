package com.service.AccountService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.AccountService.models.Account;
import com.service.AccountService.models.Transaction;

@Repository
public interface TransactionsRepo extends JpaRepository<Transaction, Long> {
	Transaction findByAccountId(Account account);
}