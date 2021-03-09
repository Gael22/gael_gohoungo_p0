package com.bank.ui;

import java.util.Scanner;

import com.bank.dao.AccountDao;
import com.bank.exception.InvalidPassword;
import com.bank.exception.UserNotFound;
import com.bank.pojo.User;
import com.bank.service.AuthService;

public class LoginMenu implements Menu {
	
	private MainMenu mainMenu;
	
	private Menu nextMenu;
	
	private AuthService authService;

	private Scanner scan;

	public Scanner getScanner() {
		return scan;
	}

	public void setScanner(Scanner scan) {
		this.scan = scan;
	}

	public AuthService getUserService() {
		return authService;
	}

	public void setUserService(AuthService userService) {
		this.authService = userService;
	}

	public Menu advance() {
		return null;
	}

	public void displayOptions() {
		System.out.println("Please Enter Username");
		String username = scan.nextLine();
		System.out.println("Please Enter Password");
		String password = scan.nextLine();
		
		User user = new User(username, password);
		
		
		try {
			authService.authenticateUser(user);
		} catch (UserNotFound e) {
			System.out.println("Username does not exist.  Please register an account.");
		} catch (InvalidPassword e) {
			System.out.println("Authentication error, check username/password");
		} catch (Exception e) {
			System.out.println("Sorry, something went wrong. Please try again later.");
			e.printStackTrace();
		} finally {
			System.out.println();
		}
		if (user != null) {
			nextMenu = mainMenu;
			System.out.println("Successfully logged in!!!!");
			System.out.println("Welcome back to your mobile Account");
		} else {
			nextMenu = null;
		}
		
		
	}

	public Menu previousMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	public LoginMenu() {
		super();
	}

	public LoginMenu(AuthService authService) {
		super();
		this.authService = authService;
	}
}