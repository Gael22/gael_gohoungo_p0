package com.bank.dao;

import java.util.List;

import com.bank.exception.CustomerException;
import com.bank.pojo.Account;
import com.bank.pojo.Customer;

public interface CustomerDao {

		public String makeAccountNumber() throws CustomerException;
		public Customer findCustomerByAccountNumber(String accountNumber) throws CustomerException;
		public List<Customer> allCustomers() throws CustomerException;
		public List<Customer> allUnreviewedCustomers() throws CustomerException;
		public void changeApprovealStatusOfCustomer(Customer customer, boolean approved) throws CustomerException;
		public void insertCustomer(Customer customer, Account account) throws CustomerException;
		public void updateCustomerAmount(Customer customer) throws CustomerException;
}
