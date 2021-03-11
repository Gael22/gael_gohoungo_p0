package com.bank.service;

import java.util.List;


import org.apache.log4j.Logger;

import com.bank.dao.CustomerDao;
import com.bank.dao.CustomerDaoPostgres;
import com.bank.exception.CustomerException;
import com.bank.pojo.Customer;
import com.bank.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerDao = new CustomerDaoPostgres();
	private static Logger log = Logger.getLogger(CustomerServiceImpl.class);

	@Override
	public String makeAccountNumber() throws CustomerException {
		return customerDao.makeAccountNumber();
	}

	@Override
	public Customer findCustomerByAccountNumber(String accountNumber) throws CustomerException {
		return customerDao.findCustomerByAccountNumber(accountNumber);
	}

	@Override
	public void changeApprovealStatusOfCustomer(Customer customer, boolean approved) throws CustomerException {
		customerDao.changeApprovealStatusOfCustomer(customer,approved);
		
	}

	@Override
	public List<Customer> allCustomers() throws CustomerException {
		log.debug("inside the service customer  all");
		return customerDao.allCustomers();
	}

	@Override
	public List<Customer> allUnreviewedCustomers() throws CustomerException {
		log.debug("getting all int unapproved customers in customer  service impl");
		return customerDao.allUnreviewedCustomers();
	}

	@Override
	public void insertCustomer(Customer customer) throws CustomerException {
		customerDao.insertCustomer(customer, null);
		
	}

	@Override
	public void updateCustomerAmount(Customer customer, double amountToChangeItBy, String type)
			throws CustomerException {
		if(type.equals("deposit"))
			customer.setAmount(customer.getAmount()+amountToChangeItBy);
		else if(type.equals("withdraw"))
			customer.setAmount(customer.getAmount()-amountToChangeItBy);
		else
			throw new CustomerException("The type of transaction doesn't exist");
		customerDao.updateCustomerAmount(customer);
		
	}

}
