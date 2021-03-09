package com.bank.dao;

import java.util.List;

import com.bank.pojo.Account;
import com.bank.exception.AccountException;

public interface AccountDao {

	public boolean doesAccountExists(String accountNumber) throws AccountException;
	public Account getAccountByAccountNumber(String accountNumber) throws AccountException;
	public List<Account> getAllAccounts() throws AccountException;
	public List<Account> getAllUnapproved() throws AccountException;
	public void insertAccount(Account account) throws AccountException;
}