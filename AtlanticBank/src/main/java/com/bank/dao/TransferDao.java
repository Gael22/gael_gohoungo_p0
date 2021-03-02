package com.bank.dao;

import java.util.List;

import com.bank.exception.TransferException;
import com.bank.pojo.Transfer;
import com.bank.pojo.User;

public interface TransferDao {

	public Transfer getTransferByID(int id) throws TransferException;
	public List<Transfer> getUnapprovedTransfersForAnAccount(String accountNumber) throws TransferException;
	public int getNumberOfUnapporvedTransfers(User user) throws TransferException;
	public void newTransfer(Transfer transfer) throws TransferException;
	public boolean approveTransfer(int id,String accountNumberOfReceiver) throws TransferException;
}
