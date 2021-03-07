package com.bank.service;

import java.util.List;

import com.bank.dao.TransferDao;
import com.bank.dao.TransferDaoImpl;
import com.bank.exception.TransferException;
import com.bank.pojo.Transfer;
import com.bank.pojo.User;

public class TransferServiceImpl implements TransferService {
	
	private TransferDao transferDao = new TransferDaoImpl();

	@Override
	public int getNumberOfUnapprovedTransfers(User user) throws TransferException {
		return transferDao.getNumberOfUnapporvedTransfers(user);
	}

	@Override
	public Transfer getTransferByID(int id) throws TransferException {
		return transferDao.getTransferByID(id);
	}

	@Override
	public List<Transfer> getUnapprovedTransfersForAnAccount(String accountNumber) throws TransferException {
		return transferDao.getUnapprovedTransfersForAnAccount(accountNumber);
	}

	@Override
	public void newTransfer(Transfer transfer) throws TransferException {
		transferDao.newTransfer(transfer);
		
	}

	@Override
	public void approveTransfer(int id, String accountNumberOfTheReceiver) throws TransferException {
		transferDao.approveTransfer(id, accountNumberOfTheReceiver);
		
	}

	

}
