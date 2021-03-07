package com.bank.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import com.bank.dao.UserDaoKryo;
import com.bank.pojo.User;

class UserDaoKryoTest {

	@Test
	void createUser() {
		fail("Not yet implemented");
	}
	
	@Test
	void testGetUserByUsername() {
		UserDaoKryo userDAO = new UserDaoKryo();
		String user;
		
		userDAO.getUserByUsername(user);
		
	
		assertEquals(user, "");

	}
}

