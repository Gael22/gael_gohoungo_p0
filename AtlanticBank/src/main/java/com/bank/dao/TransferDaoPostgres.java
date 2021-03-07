package com.bank.dao;

import java.util.List;

import com.bank.exception.TransferException;
import com.bank.pojo.Transfer;
import com.bank.pojo.User;

public class TransferDaoPostgres implements TransferDao {

	@Override
	public Transfer getTransferByID(int id) throws TransferException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transfer> getUnapprovedTransfersForAnAccount(String accountNumber) throws TransferException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfUnapporvedTransfers(User user) throws TransferException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void newTransfer(Transfer transfer) throws TransferException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean approveTransfer(int id, String accountNumberOfReceiver) throws TransferException {
		// TODO Auto-generated method stub
		return false;
	}

}
