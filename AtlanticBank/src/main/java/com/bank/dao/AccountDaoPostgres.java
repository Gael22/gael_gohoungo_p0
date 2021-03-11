package com.bank.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bank.exception.AccountException;
import com.bank.pojo.Account;
import com.bank.util.ConnectionFactoryPostgres;

public class AccountDaoPostgres implements AccountDao{
	
	Logger log = Logger.getRootLogger();

	@Override
	public boolean doesAccountExists(String accountNumber) throws AccountException {
		
		try {
			Connection conn = ConnectionFactoryPostgres.getConnection();
			String sql = "select COUNT(number) from \"atl_bank\".account where number =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, accountNumber);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next())
				if(resultSet.getInt("count")>0)
					return true;
				else
					return false;
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.trace(e.getMessage());
			throw new AccountException("ERROR INSIDE THE DOES ACCOUNT EXIST METHOD");
		}
		
	}

	@Override
	public Account getAccountByAccountNumber(String accountNumber) throws AccountException {
        Account account = null;
		
		try(Connection conn = ConnectionFactoryPostgres.getConnection()){
			String sql = "select * from \"atl_bank\".account where number=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, accountNumber);
			ResultSet resultSet = stmt.executeQuery();
			log.debug("Query Excecuted");
			if(resultSet.next())
			{
				log.debug("If in DAO");
				account = new Account(resultSet.getString("number"),resultSet.getString("password"));
			} else {
				log.debug("else in dao");
				throw new AccountException("No account under the account number " + accountNumber);
			}
			if(Double.parseDouble(account.getAccountNumber())>1000){
				account.setAccountType("Customer");
			} else {
				account.setAccountType("Employee");
			}
			
		} catch (SQLException e) {
			log.trace("exception in DAO");
			log.trace(e.getMessage());
			throw new AccountException("Internal error occured contact bank");
		}
		return account;
	}

	@Override
	public List<Account> getAllAccounts() throws AccountException {
		List<Account> accountList = new ArrayList<>();
		try (Connection conn = ConnectionFactoryPostgres.getConnection()){
			String sql = "select * from \"atl_bank\".account";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				Account account = new Account(resultSet.getString("number"),resultSet.getString("password"));
				if(Double.parseDouble(account.getAccountNumber())>=1000)
					account.setAccountType("customer");
				else
					account.setAccountType("employee");
				accountList.add(account);
			}
			if(accountList.size()==0) {
				throw new AccountException("No Accounts in the DB yet");
			}
			
		} catch(SQLException e) {
			log.trace(e.getMessage());
			throw new AccountException("Internal error occured");
		}
		return accountList;
	}

	@Override
	public List<Account> getAllUnapproved() throws AccountException {
		List<Account> accountList = new ArrayList<>();
		try(Connection conn = ConnectionFactoryPostgres.getConnection()){
			String sql = "select * from \"atl_bank\".account where account = false";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				Account account = new Account(resultSet.getString("number"),resultSet.getString("password"));
				accountList.add(account);
			}
			return accountList;
		} catch (SQLException e) {
			throw new AccountException("Internal error occured fetching accounts");
		}
		
	}

	@Override
	public void insertAccount(Account account) throws AccountException {
		Connection conn;
		try {
			conn = ConnectionFactoryPostgres.getConnection();
			String sql = "INSERT INTO \"atl_bank\".account (number,password) VALUES(?,?);";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, account.getAccountNumber());
			preparedStatement.setString(2, account.getPassword());
			if(preparedStatement.executeUpdate()!=0) {
				log.info("Account successfully added");
			}
			else {
				throw new AccountException("Account unsuccessfully added");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.trace(e);
			throw new AccountException("ERROR INSIDE ACCOUNT ADDER DAO");
		}
		
		
	}
	
	
	
}