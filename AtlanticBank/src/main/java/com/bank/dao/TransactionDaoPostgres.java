package com.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bank.exception.CustomerException;
import com.bank.exception.TransactionException;
import com.bank.pojo.Customer;
import com.bank.pojo.Transaction;
import com.bank.service.CustomerService;
import com.bank.service.CustomerServiceImpl;
import com.bank.util.ConnectionFactoryPostgres;


public class TransactionDaoPostgres implements TransactionDao {
	private static Logger log = Logger.getLogger(TransactionDaoPostgres.class);
	@Override
	public void updateCount() throws TransactionException {
		try {
			String sql = "select count(id) from atl_bank.transaction";
			Connection connection = ConnectionFactoryPostgres.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			int count = resultSet.getInt("count");
			Transaction.setCount(count);
		}catch(SQLException e) {
			log.trace("ERROR INSIDE COUNT FOR TRANSACTION DAO");
		}
		
	}

	@Override
	public Transaction getTransactionById(int id) throws TransactionException {
		try {
			String sql = "select * from atl_bank.transaction where id = ?";
			Connection connection = ConnectionFactoryPostgres.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				return (new Transaction(resultSet.getString("accountnumber"),resultSet.getInt("id"),resultSet.getDouble("previousamount"),
						resultSet.getDouble("newamount"),resultSet.getDouble("transactionamount"),resultSet.getDate("date"),resultSet.getString("type")));
			}
			throw new TransactionException ("Account not found");
			
		}catch(SQLException e) {
			log.trace("ERROR INSIDE GET ALL FOR TRANSACTION DAO");
			throw new TransactionException("Had an error finding the account");
		}

	}

	@Override
	public List<Transaction> getAllTransactions() throws TransactionException {
		List<Transaction> transactions = new ArrayList();
		try {
			String sql = "select * from atl_bank.transaction";
			Connection connection = ConnectionFactoryPostgres.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				transactions.add(new Transaction(resultSet.getString("accountnumber"),resultSet.getInt("id"),resultSet.getDouble("previousamount"),
						resultSet.getDouble("newamount"),resultSet.getDouble("transactionamount"),resultSet.getDate("date"),resultSet.getString("type")));
			}
			log.debug("all transactions given");
		}catch(SQLException e) {
			log.trace("ERROR INSIDE GET ALL FOR TRANSACTION DAO");
		}
		return transactions;
	}

	@Override
	public List<Transaction> getAllTransactionsOfACustomer(Customer customer) throws TransactionException {
		List<Transaction> transactions = new ArrayList();
		try {
			String sql = "select * from atl_bank.transaction where accountnumber = ?";
			Connection connection = ConnectionFactoryPostgres.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getAccountNumber());
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				transactions.add(new Transaction(resultSet.getString("accountnumber"),resultSet.getInt("id"),resultSet.getDouble("previousamount"),
						resultSet.getDouble("newamount"),resultSet.getDouble("transactionamount"),resultSet.getDate("date"),
						resultSet.getString("type")));
			}
			return transactions;
		}catch(SQLException e) {
			log.trace(e);
			throw new TransactionException("ERROR INSIDE THE GET ALL TRANSACTION DAO");
		}
	}

	@Override
	public void newTransaction(Transaction transaction, Customer customer) throws TransactionException {
		try {
			CustomerService customerServicer = new CustomerServiceImpl();
			customerServicer.updateCustomerAmount(customer, transaction.getTransactionAmount(), transaction.getType());
			
			String sqlForTransaction = "INSERT INTO atl_bank.transaction (accountnumber, previousamount, newamount, transactionamount, \"date\",type) VALUES(?, ?, ?, ?, ?,?);";
			Connection connection = ConnectionFactoryPostgres.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sqlForTransaction);
			preparedStatement.setString(1, transaction.getAccountNumber());
			preparedStatement.setFloat(2, Float.parseFloat(transaction.getPreviousAmount() +""));
			preparedStatement.setFloat(3, Float.parseFloat(transaction.getNewAmount()+""));
			preparedStatement.setFloat(4, Float.parseFloat(transaction.getTransactionAmount()+""));
			preparedStatement.setDate(5, (Date) transaction.getDateOf());
			preparedStatement.setString(6, transaction.getType());
			if(preparedStatement.executeUpdate()!=0) {
				log.debug("Transaction successfully added");
			}
			else {
				throw new TransactionException("Transaction unsuccessfully added");
			}
			

		}catch (SQLException e){
			
			log.trace(e);
			throw new TransactionException("ERROR INSERTING TRANSACTION");
		} catch(CustomerException e) {
			
		}
		
	}

	
}
