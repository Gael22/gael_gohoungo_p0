package com.bank.dao;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import com.bank.exception.CustomerException;
import com.bank.pojo.Customer;
import com.bank.service.CustomerService;
import com.bank.service.CustomerServiceImpl;

public class CustomerDaoPostgresTest {

	@Test
	void testUpdateCustomerAmount() {
		CustomerService customerServicer = new CustomerServiceImpl();
		try {
			Customer customerToMatch = customerServicer.findCustomerByAccountNumber("1001");
			customerToMatch.setAmount(customerToMatch.getAmount()-7);
			Customer customerChanger = customerServicer.findCustomerByAccountNumber("1001");
			customerServicer.updateCustomerAmount(customerChanger, -7,"withdraw");
			Customer customerToTest = customerServicer.findCustomerByAccountNumber("1001");
			assertEquals(customerToMatch.getAmount(),customerToTest.getAmount());
			
			
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			fail("The customer servicer for find customer by account number failed");
		}
	}
}
