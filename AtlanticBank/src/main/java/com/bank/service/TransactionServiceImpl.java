package com.bank.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.bank.dao.TransactionDao;
import com.bank.dao.TransactionDaoPostgres;
import com.bank.exception.TransactionException;
import com.bank.pojo.Transaction;
import com.bank.pojo.User;

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
	public List<Transaction> getAllTransactionsOfAUser(User user) throws TransactionException {
		return transactionDao.getAllTransactionsOfACustomer(user);
	}

	@Override
	public Transaction newTransaction(Transaction transaction, User user) throws TransactionException {
		log.debug("inserting new Transaction from Service");
		transactionDao.newTransaction(transaction, user);
		return transaction;
	}

}
