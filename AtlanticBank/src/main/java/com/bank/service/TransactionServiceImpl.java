package com.bank.service;

import java.util.List;


import org.apache.log4j.Logger;

import com.bank.dao.TransactionDao;
import com.bank.dao.TransactionDaoPostgres;
import com.bank.exception.TransactionException;
import com.bank.pojo.Customer;
import com.bank.pojo.Transaction;


public class TransactionServiceImpl implements TransactionService{
	
	private TransactionDao transactionDao = new TransactionDaoPostgres();
	private Logger log = Logger.getLogger(TransactionServiceImpl.class);
	@Override
	public void updateCount() throws TransactionException {
		log.debug("Entering updateCount for transactions");
		transactionDao.updateCount();
		
	}
	@Override
	public Transaction getTransactionById(int id) throws TransactionException {
		return transactionDao.getTransactionById(id);
	}
	@Override
	public List<Transaction> getAllTransactions() throws TransactionException {
		return transactionDao.getAllTransactions();
	}
	@Override
	public List<Transaction> getAllTransactionsOfACustomer(Customer customer) throws TransactionException {
		return transactionDao.getAllTransactionsOfACustomer(customer);
	}
	@Override
	public Transaction newTransaction(Transaction transaction, Customer customer) throws TransactionException {
		log.debug("inserting new Transaction from Service");
		transactionDao.newTransaction(transaction, customer);
		return transaction;
	}


}
