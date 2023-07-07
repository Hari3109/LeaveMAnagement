package com.cg.LeaveManagement.service;


import javax.servlet.http.HttpServletResponse;

import com.cg.LeaveManagement.Entity.Employee;
import com.cg.LeaveManagement.Exception.LeaveManagementSystemException;

public interface EmployeeService {
	public boolean login(String userName, String password, HttpServletResponse response);
	Employee addEmployee(Employee addEmployee);
	Employee getEmployeeById(Integer empid) throws LeaveManagementSystemException;
	Employee updatEmployee (Employee updateEmployee);
	Employee deletEmployeeById(Integer empid);

}
