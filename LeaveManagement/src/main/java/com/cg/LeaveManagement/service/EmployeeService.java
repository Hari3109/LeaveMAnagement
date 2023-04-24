package com.cg.LeaveManagement.service;

import com.cg.LeaveManagement.Entity.Employee;
import com.cg.LeaveManagement.Exception.LeaveManagementSystemException;

public interface EmployeeService {
	Employee addEmployee(Employee addEmployee);
	Employee getEmployeeById(Integer empid) throws LeaveManagementSystemException;
	Employee updatEmployee (Employee updateEmployee);
	Employee deletEmployeeById(Integer empid);
//	public int getLeaveBalance(int employeeId);
}
