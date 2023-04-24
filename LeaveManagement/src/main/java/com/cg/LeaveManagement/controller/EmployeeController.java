package com.cg.LeaveManagement.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.LeaveManagement.Entity.Employee;
import com.cg.LeaveManagement.Exception.LeaveManagementSystemException;
import com.cg.LeaveManagement.service.EmployeeService;


@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	

	// Add employee
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee employee) {
	    return employeeService.addEmployee(employee);
	}

	// Get employee by id
	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable("id") Integer empid) throws LeaveManagementSystemException {
	    return employeeService.getEmployeeById(empid);
	}

	// Update employee
	@PutMapping("/employee/update")
	public Employee updateEmployee(@RequestBody Employee employee) {
	   return employeeService.updatEmployee(employee);
	}

	// Delete employee
	@DeleteMapping("/employee/{id}")
	public Employee deleteEmployeeById(@PathVariable("id") Integer empid) {
	    return employeeService.deletEmployeeById(empid);
	}
	
	

}
