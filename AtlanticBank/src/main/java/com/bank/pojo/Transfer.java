package com.bank.pojo;

import java.util.Date;

public class Transfer {

	private int id;
	private String senderAccountNumber;
	private String senderName;
	private String receiverAccountNumber;
	private double amount;
	private Date dateOfCreation;
	public boolean approved;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSenderAccountNumber() {
		return senderAccountNumber;
	}
	public void setSenderAccountNumber(String senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getReceiverAccountNumber() {
		return receiverAccountNumber;
	}
	public void setReceiverAccountNumber(String receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public Transfer(int id, String senderAccountNumber, String senderName, String receiverAccountNumber, double amount,
			Date dateOfCreation, boolean approved) {
		super();
		this.id = id;
		this.senderAccountNumber = senderAccountNumber;
		this.senderName = senderName;
		this.receiverAccountNumber = receiverAccountNumber;
		this.amount = amount;
		this.dateOfCreation = dateOfCreation;
		this.approved = approved;
	}
	@Override
	public String toString() {
		return "Transfer [id=" + id + ", senderAccountNumber=" + senderAccountNumber + ", senderName=" + senderName
				+ ", receiverAccountNumber=" + receiverAccountNumber + ", amount=" + amount + ", dateOfCreation="
				+ dateOfCreation + ", approved=" + approved + "]";
	}
	
	
}
