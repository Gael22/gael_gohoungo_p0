package com.bank.service;

import java.util.List;

import com.bank.exception.TransferException;
import com.bank.pojo.Transfer;
import com.bank.pojo.User;

public interface TransferService {

	public int getNumberOfUnapprovedTransfers(User user) throws TransferException;
	public Transfer getTransferByID(int id) throws TransferException;
	public List<Transfer> getUnapprovedTransfersForAnAccount(String accountNumber) throws TransferException;
	public void newTransfer(Transfer transfer) throws TransferException;
	public void approveTransfer(int id, String accountNumberOfTheReceiver) throws TransferException;
}
