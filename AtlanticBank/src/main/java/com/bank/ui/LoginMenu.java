package com.bank.ui;

import java.util.Scanner;

public class LoginMenu implements Menu {
	
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

}
