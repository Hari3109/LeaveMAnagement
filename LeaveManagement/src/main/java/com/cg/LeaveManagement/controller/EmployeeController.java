package com.cg.LeaveManagement.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.LeaveManagement.Entity.Employee;
import com.cg.LeaveManagement.Exception.JwtTokenMalformedException;
import com.cg.LeaveManagement.Exception.JwtTokenMissingException;
import com.cg.LeaveManagement.Exception.LeaveManagementSystemException;
import com.cg.LeaveManagement.jwt.util.JWTUtils;
import com.cg.LeaveManagement.service.EmployeeService;


@RestController
public class EmployeeController {
	@Autowired
	
	private EmployeeService employeeService;
	@PostMapping("/employeelogin")
    public String login(@RequestBody Map<String, String> loginRequest,HttpServletResponse response) throws JwtTokenMalformedException, JwtTokenMissingException {
        String userName = loginRequest.get("userName");
        String password = loginRequest.get("password");
        if (employeeService.login(userName, password,response)) {
            return "Login successful";
        } else {
            return "Invalid username or password";
        }
    }
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee employee)  {
	    return employeeService.addEmployee(employee);
	}
	@GetMapping("/employee/{id}/leave-balance")
    public int getLeaveBalance(@PathVariable("id") int employeeId) throws LeaveManagementSystemException {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return employee.getLeavesAvailable();
    }


	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable("id") Integer empid,HttpServletRequest response) throws LeaveManagementSystemException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(response);
	    return employeeService.getEmployeeById(empid);
	}


	@PutMapping("/employee/update")
	public Employee updateEmployee(@RequestBody Employee employee,HttpServletRequest response) throws JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(response);
	   return employeeService.updatEmployee(employee);
	}


	@DeleteMapping("/employee/{id}")
	public Employee deleteEmployeeById(@PathVariable("id") Integer empid,HttpServletRequest response) throws JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(response);
	    return employeeService.deletEmployeeById(empid);
	}



}
