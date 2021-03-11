package com.bank.dao;

import java.util.List;

import com.bank.exception.EmployeeException;
import com.bank.pojo.Employee;

public interface EmployeeDao {

	public List<Employee> getAllEmployees() throws EmployeeException;
	public Employee findEmployeeByAccountNumber(String accountNumber) throws EmployeeException;
}
