package com.cg.LeaveManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.LeaveManagement.Entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	

}
