package com.cg.LeaveManagement.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.LeaveManagement.Entity.Employee;
import com.cg.LeaveManagement.Exception.LeaveManagementSystemException;
import com.cg.LeaveManagement.Repository.EmployeeRepository;
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

//	@Override
//	public int getLeaveBalance(int employeeId) {
//		Optional<Employee> employee = employeeRepository.findById(employeeId);
//        return employeeRepository.getLeaveBalance();
//   }

}
