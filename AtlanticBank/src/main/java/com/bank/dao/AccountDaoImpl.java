package com.bank.dao;

import java.util.List;

import com.bank.exception.AccountException;
import com.bank.pojo.Account;

public class AccountDaoImpl implements AccountDao{

	@Override
	public boolean doesAccountExists(String accountNumber) throws AccountException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account getAccountByAccountNumber(String accountNumber) throws AccountException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllAccounts() throws AccountException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllUnapproved() throws AccountException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertAccount(Account account) throws AccountException {
		// TODO Auto-generated method stub
		
	}

	
}
