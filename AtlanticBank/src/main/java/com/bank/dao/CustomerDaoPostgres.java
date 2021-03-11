package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bank.exception.AccountException;
import com.bank.exception.CustomerException;
import com.bank.pojo.Account;
import com.bank.pojo.Customer;
import com.bank.service.AccountService;
import com.bank.service.AccountServiceImpl;
import com.bank.util.ConnectionFactoryPostgres;

public class CustomerDaoPostgres implements CustomerDao{
	
	private static Logger log = Logger.getLogger(CustomerDaoPostgres.class);
	private AccountService accountServicer = new AccountServiceImpl();

	@Override
	public String makeAccountNumber() throws CustomerException {
		log.debug("finding the next accountNumber");
		try {
			Connection connection = ConnectionFactoryPostgres.getConnection();
			String sql = "Select MAX(accountnumber) from atl_bank.customer";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			String output =null;
			while(resultSet.next()) {
				output =  resultSet.getString("max");
			}
			double outputIntoDouble = Double.parseDouble(output);
			outputIntoDouble++;
			output = String.valueOf(Double.valueOf(outputIntoDouble).intValue());
			return output;
		}catch (SQLException e) {
			log.trace(e);
			throw new CustomerException("ERROR INSIDE THE MAKE ACCOUNT NUMBER METHOD");
		}
	}

	@Override
	public Customer findCustomerByAccountNumber(String accountNumber) throws CustomerException {
		log.debug("starting the DAO search");
		try {
			Connection conn = ConnectionFactoryPostgres.getConnection();
			String sql = "Select * from atl_bank.customer where accountnumber=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1,accountNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				return new Customer(resultSet.getString("name"),resultSet.getString("accountnumber"),resultSet.getDate("dateOfBirth"),resultSet.getDate("creationDate"),resultSet.getString("type"),resultSet.getDouble("amount"),resultSet.getBoolean("approved"),resultSet.getBoolean("reviewed"));
			}
			
		} catch (SQLException  e){
			log.trace("Error in the find the customer by account number DAO");
			throw new CustomerException("ERROR IN THE FIND CUSTOMER BY ACCOUNT DAO");
		}
		return null;
	}

	@Override
	public List<Customer> allCustomers() throws CustomerException {
        List<Customer> customers= new ArrayList();
		
		try {
			log.debug("Starting connection for all customers");
			Connection conn = ConnectionFactoryPostgres.getConnection();
			String sql = "Select * from atl_bank.customer";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			log.debug("entering customers into the list");
			while(resultSet.next())
			{
				Customer customer = new Customer(resultSet.getString("name"),resultSet.getString("accountnumber"),resultSet.getDate("\"dateOfBirth\""),resultSet.getDate("\"creationDate\""),resultSet.getString("type"),resultSet.getDouble("amount"),resultSet.getBoolean("approved"),resultSet.getBoolean("reviewed"));
				customers.add(customer);
			}
		}catch(SQLException  e) {
			log.trace(e.getMessage());
			throw new CustomerException("Error inside get all customers connection");
		}
		
		return customers;
	}

	@Override
	public List<Customer> allUnreviewedCustomers() throws CustomerException {
        List<Customer> customers= new ArrayList();
		
		try {
			log.debug("Starting connection for all unapproved customers");
			Connection connection = ConnectionFactoryPostgres.getConnection();
			String sql = "select * from atl_bank.customer c where c.reviewed = false";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			log.debug("entering customers into the list");
			while(resultSet.next())
			{
				Customer customer = new Customer(resultSet.getString("name"),resultSet.getString("accountnumber"),resultSet.getDate("dateOfBirth"),resultSet.getDate("creationDate"),resultSet.getString("type"),resultSet.getDouble("amount"),resultSet.getBoolean("approved"),resultSet.getBoolean("reviewed"));
				customers.add(customer);
			}
		}catch(SQLException  e) {
			log.trace(e.getMessage());
			throw new CustomerException("Error inside customers unapproved connection");
		}
		
		return customers;
	}

	@Override
	public void changeApprovealStatusOfCustomer(Customer customer, boolean approved) throws CustomerException {
		try {
			log.debug("Starting connection for the approval process");
			Connection connection = ConnectionFactoryPostgres.getConnection();
			String sql ="UPDATE atl_bank.customer set approved = ?, reviewed = true where accountnumber = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, approved);
			preparedStatement.setString(2, customer.getAccountNumber());
			int output = preparedStatement.executeUpdate();
			//log.debug(output);
			if(output!=0) {
				customer.setApproved(approved);
				customer.setReviewed(true);
				log.info("Successefull update of "+ customer.getName()+" account.");
			} else {
				throw new CustomerException("Error in update");
			}
			
		}catch(SQLException e) {
			log.debug(e);
			throw new CustomerException("ERROR INSIDE THE CONNECTOR FOR THE APPROVAL PROCESS");
		}
		
	}

	@Override
	public void insertCustomer(Customer customer, Account account) throws CustomerException {
		log.debug("Inserting customer from the DAO");
		Connection connection;
		try {
			connection = ConnectionFactoryPostgres.getConnection();
			accountServicer.insertAccount(account);
			customer.setAccountNumber(account.getAccountNumber());
			String sql = "INSERT INTO atl_bank.customer (name,accountnumber,\"dateOfBirth\",\"creationDate\",type,amount,approved) VALUES(?,?,?,?,?,?,false);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2,	customer.getAccountNumber());
			preparedStatement.setDate(3, customer.getDateOfBirth());
			preparedStatement.setDate(4, customer.getCreationDate());
			preparedStatement.setString(5, customer.getType());
			preparedStatement.setDouble(6, customer.getAmount());
			
			if(preparedStatement.executeUpdate()!=0) {
				log.info("Your successfully Registered");
			}
			else {
				
				throw new CustomerException("An error occured please contact customer service");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.trace("ERROR INSIDE OF DAO FOR INSERT CUSTOMER");
			log.trace(e);
			throw new CustomerException("An error occured please contact customer service");
		}catch( AccountException e) {
			throw new CustomerException(e.getMessage());
		}

		
	}

	@Override
	public void updateCustomerAmount(Customer customer) throws CustomerException {
		log.debug("Starting the update customer amount process");
		try {
			Connection connection = ConnectionFactoryPostgres.getConnection();
			String sql = "UPDATE atl_bank.customer\r\n"
					+ "	SET amount=?\r\n"
					+ "	WHERE accountnumber=?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, customer.getAmount());
			preparedStatement.setString(2, customer.getAccountNumber());
			if(preparedStatement.executeUpdate()!=0) {
				return;
			}
			else {
				
				throw new CustomerException("An error occured please contact customer service");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.trace(e.getMessage());
			throw new CustomerException("Error at updateCustomerAmountDAO");
		}
		
	}

}
