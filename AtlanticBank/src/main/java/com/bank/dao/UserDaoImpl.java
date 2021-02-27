package com.bank.dao;

import java.util.Iterator;
import java.util.List;

import com.bank.exception.UserNametaken;
import com.bank.exception.UserNotFound;
import com.bank.pojo.User;

public class UserDaoImpl implements UserDao {
	
	public static List<User> userlist;

	public void createUser(User user) throws UserNametaken {
		
		if (user.getUsername() != null && user.getPassword() != null) {
			// Iterate inside User list Object
			Iterator<User> iter = userlist.iterator();
			
			while (iter.hasNext()) {
				if (iter.next().getUsername().equals(user.getUsername())) {
					//If user name Exist throw UserNameTaken exception
					throw new UserNametaken();
				}
			}
			//if User name not taken, add user
			userlist.add(user);
		}

	}

	public User getUserByUsername(String username) throws UserNotFound  {
		
		Iterator<User> iter = userlist.iterator();
		
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

}
