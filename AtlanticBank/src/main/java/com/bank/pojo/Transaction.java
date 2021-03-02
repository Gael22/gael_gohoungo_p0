package com.bank.pojo;

import java.util.Date;

public class Transaction {

	private int id;
	private String accountNumber;
	private double previousAmount;
	private double newAmount;
	private double transactionAmount;
	private Date dateOf;
	private String type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getPreviousAmount() {
		return previousAmount;
	}
	public void setPreviousAmount(double previousAmount) {
		this.previousAmount = previousAmount;
	}
	public double getNewAmount() {
		return newAmount;
	}
	public void setNewAmount(double newAmount) {
		this.newAmount = newAmount;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public Date getDateOf() {
		return dateOf;
	}
	public void setDateOf(Date dateOf) {
		this.dateOf = dateOf;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Transaction(int id, String accountNumber, double previousAmount, double newAmount, double transactionAmount,
			Date dateOf, String type) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.previousAmount = previousAmount;
		this.newAmount = newAmount;
		this.transactionAmount = transactionAmount;
		this.dateOf = dateOf;
		this.type = type;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", accountNumber=" + accountNumber + ", previousAmount=" + previousAmount
				+ ", newAmount=" + newAmount + ", transactionAmount=" + transactionAmount + ", dateOf=" + dateOf
				+ ", type=" + type + "]";
	}
	
}
