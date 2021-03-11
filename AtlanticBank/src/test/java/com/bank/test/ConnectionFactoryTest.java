package com.bank.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import com.bank.util.ConnectionFactoryPostgres;


public class ConnectionFactoryTest {

	@BeforeEach
	private void setUp() {
	}
	
	@Test
     public void test() throws ClassNotFoundException, SQLException {
		
		assertNotNull(ConnectionFactoryPostgres.getConnection(), "Connection to DB should be created.");
		
	}
}
