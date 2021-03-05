package com.bank.dao;


import java.util.List;

import com.bank.exception.UserNameTaken;
import com.bank.exception.UserNotFound;
import com.bank.pojo.User;

public interface UserDao {

	public void createUser(User user) throws UserNameTaken;
	
	public User getUserByUsername(String username) throws UserNotFound;
	
	public List<User> getAllUsers();
	
	public void updateUser(User user);
	
	public void removeUser(User user);
	
}
