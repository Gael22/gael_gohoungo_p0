package com.bank.service;

import java.util.List;

import com.bank.exception.TransactionException;
import com.bank.pojo.Transaction;
import com.bank.pojo.User;

public interface TransactionService {

	//GET
		public void updateCount() throws TransactionException;
		public Transaction getTransactionById(int id) throws TransactionException;
		public List<Transaction> getAllTransactions() throws TransactionException;
		public List<Transaction> getAllTransactionsOfAUser(User user) throws TransactionException;
		
		//PUT
		public Transaction newTransaction(Transaction transaction, User user) throws TransactionException;
}
