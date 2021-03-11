package com.bank.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.bank.exception.TransferException;
import com.bank.pojo.Customer;
import com.bank.pojo.Transfer;


public class TransferDaoPostgresTest {
	@Test
	void testGetTransferByID() {
		TransferDao transferDao = new TransferDaoPostgres();
		Transfer testerTransfer = new Transfer("1001","1002",5,Date.valueOf("2021-01-12"),false,2,"TestAccount");
		try {
			Transfer outputedTransfer = transferDao.getTransferByID(2);
			assertEquals(testerTransfer.getAmount(),outputedTransfer.getAmount());
			assertEquals(testerTransfer.getReceiverAccountNumber(),outputedTransfer.getReceiverAccountNumber());
			assertEquals(testerTransfer.getSenderAccountNumber(),outputedTransfer.getSenderAccountNumber());
			assertEquals(testerTransfer.getDateOfCreation(),outputedTransfer.getDateOfCreation());
		} catch (TransferException e) {
			// TODO Auto-generated catch block
			fail(e);
		}
	}

	@Test
	void testGetUnapprovedTransfersForAnAccount() {
		TransferDao transferDao = new TransferDaoPostgres();
		try {
			List<Transfer> transfers = transferDao.getUnapprovedTransfersForAnAccount("1001");
			for(Transfer t : transfers) {
				assertEquals(t.getAmount(),15);
				assertEquals(t.getSenderAccountNumber(),"1002");
				
			}
		} catch (TransferException e) {
			// TODO Auto-generated catch block
			fail(e);
		}
		
	}

//	 insert test has to be commented out so that random transfers don't happen while testing
//	@Test 
//	void testNewTransfer() {
//		CustomerService customerService = new CustomerServiceImpl();
//		Transfer transferToInsert;
//		try {
//			transferToInsert = new Transfer(customerService.findCustomerByAccountNumber("1001"),"1002",12);
//			TransferDAO transferDAO = new TransferDAOImpl();
//			try {
//				transferDAO.newTransfer(transferToInsert);
//			} catch (TransferException e) {
//				// TODO Auto-generated catch block
//				fail(e);
//			}
//		} catch (CustomerException e1) {
//			// TODO Auto-generated catch block
//			fail(e1);
//		}
//
//	}

	@Test
	void testApproveTransfer() {
		TransferDao transferDao = new TransferDaoPostgres();
		try {
			assertTrue(transferDao.approveTransfer(2, "1002"));
		} catch (TransferException e) {
			// TODO Auto-generated catch block
			fail(e);
		}
	}

	@Test
	void testGetNumberOfUnapporvedTransfers() {
		TransferDao transferDao = new TransferDaoPostgres();
		try {
			Customer customerToTestWith = new Customer();
			customerToTestWith.setAccountNumber("1001");
			assertEquals(1,transferDao.getNumberOfUnapporvedTransfers(customerToTestWith));
		} catch(Exception e) {
			fail(e);
		}
	}

}
