package com.service.AccountService.service;

import com.service.AccountService.models.Account;
import com.service.AccountService.models.Transaction;

public interface ITransactionService {
	Transaction addInitialCredit(Account account);
	boolean addCredit(Long accountId, double credit);
	boolean substractCredit(Long accountId, double credit);
	Long getTransactionsFromAccount(Account account);
}