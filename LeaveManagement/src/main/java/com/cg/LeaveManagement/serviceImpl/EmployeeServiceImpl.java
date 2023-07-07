package com.cg.LeaveManagement.serviceImpl;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.LeaveManagement.Entity.Employee;
import com.cg.LeaveManagement.Exception.LeaveManagementSystemException;
import com.cg.LeaveManagement.Repository.EmployeeRepository;
import com.cg.LeaveManagement.jwt.util.JWTUtils;
import com.cg.LeaveManagement.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee addEmployee) {
        return employeeRepository.save(addEmployee);
    }

    @Override
    public Employee getEmployeeById(Integer empid) throws LeaveManagementSystemException {
        Optional<Employee> employee = employeeRepository.findById(empid);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new LeaveManagementSystemException("Employee not found for id: " + empid);
        }
    }



	@Override
	public Employee updatEmployee(Employee updateEmployee) {
		 return employeeRepository.save(updateEmployee);
	}

	@Override
	public Employee deletEmployeeById(Integer empid) {
		 Optional<Employee> employee = employeeRepository.findById(empid);
	        if (employee.isPresent()) {
	            employeeRepository.deleteById(empid);
	            return employee.get();
	        } else {
	            return null;
	        }
	}

	@Override
	public boolean login(String userName, String password, HttpServletResponse response) {
		 if (userName == null || userName.trim().isEmpty()) {
		        throw new IllegalArgumentException("Username cannot be null or empty");
		    }
		    if (password == null || password.trim().isEmpty()) {
		        throw new IllegalArgumentException("Password cannot be null or empty");
		    }
		    if (password.length() < 8) {
		        throw new IllegalArgumentException("Password must be at least 8 characters long");
		    }
		    if (!password.matches(".*\\d.*")) {
		        throw new IllegalArgumentException("Password must contain at least one number");
		    }
		    if (!password.matches(".*[!@#$%^&*()].*")) {
		        throw new IllegalArgumentException("Password must contain at least one special character");
		    }
		Employee employee = employeeRepository.findByuserName(userName);
        if (employee == null) {
            return false;
        }
        String token=JWTUtils.generateToken(employee.getEmpId().toString());
        response.setHeader("Authorization", token);
        response.addHeader("token", token);
        response.addHeader("Access-Control-Expose-Headers", "token");
        return employee.getPassword().equals(password);
	}

	}


