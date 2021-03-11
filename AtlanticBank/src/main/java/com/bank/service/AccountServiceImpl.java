package com.bank.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.bank.pojo.Account;
import com.bank.dao.AccountDao;
import com.bank.dao.AccountDaoPostgres;
import com.bank.exception.AccountException;


public class AccountServiceImpl implements AccountService{
	
	private AccountDao accountDao = new AccountDaoPostgres();
	private static Logger log = Logger.getLogger(AccountServiceImpl.class);

	@Override
	public boolean doesAccountExists(String accountNumber) throws AccountException {
		return accountDao.doesAccountExists(accountNumber);
	}

	@Override
	public Account getAccountByAccountNumber(String accountNumber) throws AccountException {
		log.debug("AccountSearchService within getAccountNumber with accountNumber = " + accountNumber);
		return accountDao.getAccountByAccountNumber(accountNumber);
	}

	@Override
	public List<Account> getAllAccounts() throws AccountException {
		log.debug("getting all accounts");
		return accountDao.getAllAccounts();
	}

	@Override
	public void insertAccount(Account account) throws AccountException {
		log.debug("inserting account");
		accountDao.insertAccount(account);
		
	}
	
		
	}

	

