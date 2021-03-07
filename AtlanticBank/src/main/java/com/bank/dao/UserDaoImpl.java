package com.bank.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bank.exception.UserNameTaken;
import com.bank.exception.UserNotFound;
import com.bank.pojo.User;

public class UserDaoImpl implements UserDao {
	
	public static List<User> userList;

	public void createUser(User user) throws UserNameTaken {
		
		if (user.getUsername() != null && user.getPassword() != null) {
			// Iterate inside User list Object
			Iterator<User> iter = userList.iterator();
			
			while (iter.hasNext()) {
				if (iter.next().getUsername().equals(user.getUsername())) {
					//If user name Exist throw UserNameTaken exception
					throw new UserNameTaken();
				}
			}
			//if User name not taken, add user
			userList.add(user);
		}

	}

	public User getUserByUsername(String username) throws UserNotFound  {
		
		Iterator<User> iter = userList.iterator();
		
		while (iter.hasNext()) {
			
		    User existingUser = iter.next();	
			if (existingUser.getUsername().equals(username)) {
				return existingUser;
			}
		}
		throw new UserNotFound();
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	public void removeUser(User user) {
		// TODO Auto-generated method stub

	}

	public UserDaoImpl() {
		super();
		userList = new ArrayList<>();
		userList.add(new User("John Doe", "1234"));
		userList.add(new User("Gael G", "GG"));
		userList.add(new User("Will Smith", "ws"));
	}

}
