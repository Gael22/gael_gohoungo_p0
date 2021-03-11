package com.bank.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.bank.dao.EmployeeDao;
import com.bank.dao.EmployeeDaoPostgres;
import com.bank.exception.EmployeeException;
import com.bank.pojo.Employee;

public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeeDao employee = new EmployeeDaoPostgres();
	private static Logger log = Logger.getLogger(EmployeeServiceImpl.class);

	@Override
	public List<Employee> getAllEmployees() throws EmployeeException {
		log.debug("getting all the employees");
		return employee.getAllEmployees();
	}

	@Override
	public Employee findEmployeeByAccountNumber(String accountNumber) throws EmployeeException {
		log.debug("Inside the employee service searcher by account number = " + accountNumber);
		return employee.findEmployeeByAccountNumber(accountNumber);
	}

}
