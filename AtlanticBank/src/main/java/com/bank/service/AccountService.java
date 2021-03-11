package com.bank.service;

import java.util.List;

import com.bank.pojo.Account;
import com.bank.exception.AccountException;

public interface AccountService {
	
	public boolean doesAccountExists(String accountNumber) throws AccountException;
	public Account getAccountByAccountNumber(String accountNumber) throws AccountException;
	public List<Account> getAllAccounts() throws AccountException;
	public void insertAccount(Account customer) throws AccountException;
}
