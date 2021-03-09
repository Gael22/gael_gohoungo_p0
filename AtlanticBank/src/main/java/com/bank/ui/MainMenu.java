package com.bank.ui;

import java.util.Scanner;

import com.bank.dao.AccountDaoPostgres;
import com.bank.pojo.Account;
import com.bank.pojo.User;
import com.bank.service.AuthService;

public class MainMenu implements Menu {
	
	private Scanner scan;
	

	private AuthService authService;
	
	private AccountDaoPostgres accountDao;
	
	private User user;
	
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Menu advance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void displayOptions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Menu previousMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scanner getScanner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setScanner(Scanner scan) {
		// TODO Auto-generated method stub
		
	}


}
