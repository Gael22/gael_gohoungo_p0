package com.bank.ui;

import java.util.Scanner;

public interface Menu {

	public Menu advance();
	
	public void dispalyOptions();
	
	public Menu previousMenu();
	
	public Scanner getScanner();
	
	public void setScanner(Scanner scan);
}
