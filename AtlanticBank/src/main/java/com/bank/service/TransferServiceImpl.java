package com.bank.service;

import java.util.List;

import com.bank.exception.TransactionException;
import com.bank.pojo.Transaction;
import com.bank.pojo.User;

public class TransferServiceImpl implements TransferService {

	@Override
	public void updateCount() throws TransactionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Transaction getTransactionById(int id) throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAllTransactions() throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAllTransactionsOfACustomer(User user) throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction newTransaction(Transaction transaction, User user) throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

}
