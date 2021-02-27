package com.bank.ui;

import java.util.Scanner;

import com.bank.pojo.User;
import com.bank.service.AuthService;

public class LoginMenu implements Menu {
	
	private AuthService authService;
	
	private Scanner scan;

	public Menu advance() {
		// TODO Auto-generated method stub
		return null;
	}

	public void dispalyOptions() {
		System.out.println("Please Enter Username");
		String username = scan.nextLine();
		System.out.println("Please Enter password");
		String password = scan.nextLine();
		
		User user = new User(username, password);
		
		authService.authenticateUser(user);
		
	}

	public Menu previousMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	public Scanner getScanner() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setScanner(Scanner scan) {
		// TODO Auto-generated method stub
		
	}

	public AuthService getAuthService() {
		return authService;
	}

	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	

}
