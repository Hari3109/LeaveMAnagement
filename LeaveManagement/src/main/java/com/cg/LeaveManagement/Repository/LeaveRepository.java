package com.cg.LeaveManagement.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.LeaveManagement.Entity.Employee;
import com.cg.LeaveManagement.Entity.LeaveApplication;
import com.cg.LeaveManagement.Entity.LeaveStatus;
@Repository
public interface LeaveRepository extends JpaRepository<LeaveApplication,Integer> {

	

	List<LeaveApplication> findByEmployeeEmpIdAndStatusAndStartDateBetween(int employeeId, LeaveStatus approved,
			LocalDate startOfMonth, LocalDate endOfMonth);

	LeaveApplication findTopByEmployeeOrderByEndDateDesc(Employee emp);

//	List<LeaveApplication> findByManagerIdAndStatus(Integer managerId, LeaveStatus pending);


}
