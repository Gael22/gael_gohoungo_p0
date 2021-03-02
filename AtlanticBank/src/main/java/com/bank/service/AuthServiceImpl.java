package com.bank.service;

import com.bank.dao.UserDao;

import com.bank.exception.InvalidPassword;
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

	public boolean exixtingUser(User user) {
		try {
			if (userDao.getUserByUsername(user.getUsername()) != null) {
				return true;
			}
		} catch (UserNotFound e) {
			return false;
		}
		return false;
	}

	public User authenticateUser(User user) throws  UserNotFound, InvalidPassword {
		User existingUser = userDao.getUserByUsername(user.getUsername());
		if(existingUser.getPassword().equals(user.getPassword())) {
			return existingUser;
		}
		throw new InvalidPassword();
	}

	public User registerUser(User user) {
		// TODO Auto-generated method stub
		return null;
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
