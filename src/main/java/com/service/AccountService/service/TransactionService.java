package com.service.AccountService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.AccountService.models.Account;
import com.service.AccountService.models.Transaction;
import com.service.AccountService.repositories.TransactionsRepo;

@Service
public class TransactionService implements ITransactionService {
	
	@Autowired
	private TransactionsRepo transactionsRepo;
	
	@Override
	public Transaction addInitialCredit(Account account) {
		Transaction transaction = new Transaction(account);

		transaction.addTransaction();
		transactionsRepo.save(transaction);

		return transaction;
	}
	@Override
	public boolean addCredit(Long accountId, double credit) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean substractCredit(Long accountId, double credit) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Long getTransactionsFromAccount(Account account) {
		Transaction transaction = transactionsRepo.findByAccountId(account);
		if (transaction != null) {
			return transaction.getNumberOfTransactions();
		}
		return null;
		
	}
}