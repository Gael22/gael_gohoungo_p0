package com.bank.dao;

import java.util.List;


import com.bank.exception.TransactionException;
import com.bank.pojo.Customer;
import com.bank.pojo.Transaction;

public interface TransactionDao {

	public void updateCount() throws TransactionException;
	public Transaction getTransactionById(int id) throws TransactionException;
	public List<Transaction> getAllTransactions() throws TransactionException;
	public List<Transaction> getAllTransactionsOfACustomer(Customer customer) throws TransactionException;
	public void newTransaction(Transaction transaction,Customer customer) throws TransactionException;
}
