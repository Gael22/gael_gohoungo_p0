package com.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.bank.exception.UserNameTaken;
import com.bank.exception.UserNotFound;
import com.bank.pojo.User;
import com.bank.util.ConnectionFactoryPostgres;

public class UserDaoPostgres implements UserDao {
	
	Logger log = Logger.getRootLogger();

	@Override
	public void createUser(User user) throws UserNameTaken {
		
        log.trace("UserDaoPostgres.createUser method called");
		
		Connection conn = ConnectionFactoryPostgres.getConnection();
		
		String sql = "insert into atl_bank.user (username, pass_word) values ('" + user.getUsername() + "', '" +user.getPassword() + "')";
		
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public User getUserByUsername(String username) throws UserNotFound {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
		}

		User user = null;
		
		String url = "jdbc:postgresql://" + System.getenv("ATLBank_DB_URL") + ":5432/" + "postgres" + "?";
		
		String usr = System.getenv("ATLBank_DB_USERNAME");
		
		String password = System.getenv("ATLBank_DB_PASSWORD");
		
		//log.info("usr : " + usr);
		//log.info("password : " + password);
		
		try (Connection conn = DriverManager.getConnection(url, usr, password);) {
			
			//conn.setSchema(schema);
			
			String sql = "select * from atl_bank.user where username = '" + username + "'";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				log.info("User found in DB");
				user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("pass_word"));
			}
			
		} catch (SQLException e) {
			log.error("Failure to connect to DB", e);
		}
		
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		/* Connection conn = null;
		 PreparedStatement stmt = null;
		 
		 String sql = "select * from user where = Where username = ?";
		 
		 conn = ConnectionFactoryPostgres.getConnection();
		 
		 try {
			conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		return null;
	}

	@Override
	public void updateUser(User user, String new_password) {
        Connection conn = null;
		
		PreparedStatement stmt = null;
		
		String sql = "update user set password = ? WHERE username = ?";
		
		conn = ConnectionFactoryPostgres.getConnection();
		
		try {
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, new_password);
			stmt.setString(2, user.getUsername());
			stmt.execute();
			log.info("User updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Unsuccessful update");
		}
		
	}

	@Override
	public void removeUser(User user) {
		
       log.trace("UserDaoPostgres.createUser method called");
		
		Connection conn = ConnectionFactoryPostgres.getConnection();
		
		String sql = "delete from user " + 
					  "where username = '" + user.getUsername() + "' and password = '" +user.getPassword() + "'";
		
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
