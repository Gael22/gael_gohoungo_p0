package com.bank.pojo;

public class Account {

	private String accountNumber;
	private String password;
	private String type;
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Account(String accountNumber, String password, String type) {
		super();
		this.accountNumber = accountNumber;
		this.password = password;
		this.type = type;
	}
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", password=" + password + ", type=" + type + "]";
	}
	
}
