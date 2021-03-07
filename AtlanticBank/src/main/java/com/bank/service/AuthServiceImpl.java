package com.bank.service;

import com.bank.dao.UserDao;

import com.bank.exception.InvalidPassword;
import com.bank.exception.UserNameTaken;
import com.bank.exception.UserNotFound;
import com.bank.pojo.User;

public class AuthServiceImpl implements AuthService {
	
	private UserDao userDao;


	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
    @Override
	public boolean existingUser(User user) {
    	try {
			if (userDao.getUserByUsername(user.getUsername()) != null) {
				return true;
			}
		} catch (UserNotFound e) {
			return false;
		}
		return false;
	}
   @Override
	public User authenticateUser(User user) throws  UserNotFound, InvalidPassword {
		User existingUser = userDao.getUserByUsername(user.getUsername());
		if(existingUser.getPassword().equals(user.getPassword())) {
			return existingUser;
		}
		throw new InvalidPassword();
	}
    
   @Override
	public User registerUser(User user) throws UserNameTaken {
	   userDao.createUser(user);
		return user;
	}
	public AuthServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

}
