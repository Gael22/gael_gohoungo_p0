package com.bank.service;

import com.bank.pojo.User;

public interface AuthService {

	public boolean exixtingUser(User user);
	
	public User authenticateUser(User user);
	
	public User registerUser(User user);
}
