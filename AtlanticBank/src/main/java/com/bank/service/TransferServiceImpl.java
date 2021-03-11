package com.bank.service;

import java.util.List;

import com.bank.dao.TransferDao;
import com.bank.dao.TransferDaoPostgres;
import com.bank.exception.TransferException;
import com.bank.pojo.Customer;
import com.bank.pojo.Transfer;


public class TransferServiceImpl implements TransferService {
	
	private TransferDao transferDao = new TransferDaoPostgres();

	@Override
	public int getNumberOfUnapprovedTransfers(Customer customer) throws TransferException {
		return transferDao.getNumberOfUnapporvedTransfers(customer);
	}

	@Override
	public Transfer getTransferByID(int id) throws TransferException {
		return transferDao.getTransferByID(id);
	}

	@Override
	public List<Transfer> getUnapprovedTransfersForAnAccount(String accountNumberOfReceiver) throws TransferException {
		return transferDao.getUnapprovedTransfersForAnAccount(accountNumberOfReceiver);
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
