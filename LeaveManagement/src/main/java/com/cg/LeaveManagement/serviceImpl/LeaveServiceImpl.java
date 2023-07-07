package com.cg.LeaveManagement.serviceImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.LeaveManagement.Entity.Employee;
import com.cg.LeaveManagement.Entity.LeaveApplication;
import com.cg.LeaveManagement.Entity.LeaveStatus;
import com.cg.LeaveManagement.Exception.LeaveManagementSystemException;
import com.cg.LeaveManagement.Repository.EmployeeRepository;
import com.cg.LeaveManagement.Repository.LeaveRepository;
import com.cg.LeaveManagement.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService{
	@Autowired
	private LeaveRepository leaveRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
	@Override
	public LeaveApplication applyLeave(int employeeId, String name, LocalDate startDate, LocalDate endDate) throws LeaveManagementSystemException {

		if (endDate.isBefore(startDate)) {
            throw new LeaveManagementSystemException("End date cannot be before start date");
        }
        int daysApplied = calculateDaysApplied(startDate, endDate);
        if (daysApplied <= 0) {
            throw new LeaveManagementSystemException("Invalid leave duration");
        }

        Employee emp= employeeRepository.findById(employeeId).orElseThrow(()->new LeaveManagementSystemException("Employee not found"));
        int leavesTakenThisMonth = getLeavesThisMonth(employeeId);
        if(leavesTakenThisMonth+daysApplied>2) {
        	throw new LeaveManagementSystemException("Days appiled exceeds monthly limit");
        }
        LeaveApplication lastLeave=leaveRepository.findTopByEmployeeOrderByEndDateDesc(emp);
        if(lastLeave !=null&& lastLeave.getEndDate().isAfter(LocalDate.now().withDayOfMonth(1))) {
        	int UnutilizedLeave= lastLeave.getDaysApplied()-(getDaysBetween(lastLeave.getStartDate(),lastLeave.getEndDate())+1);
        	if(UnutilizedLeave>0) {
        	 daysApplied=Math.min(daysApplied, UnutilizedLeave);
        	}
        }
        LocalDate leaveEnd=startDate.plusDays(daysApplied-1);
        if (leaveEnd.isAfter(LocalDate.now().withMonth(12).withDayOfMonth(31))) {
            throw new LeaveManagementSystemException("Leave cannot be carried forward to next year");
        }
        emp.setEmpId(employeeId);

        LeaveApplication leave = new LeaveApplication(startDate, endDate, daysApplied,emp,LeaveStatus.PENDING);
        leaveRepository.save(leave);
        return leave;

	}




	private int getDaysBetween(LocalDate startDate, LocalDate endDate) {
		int days=0;
		LocalDate date=startDate;
		while(date.isBefore(endDate)) {
			days++;
			date=date.plusDays(1);
		}
				return days;
	}




	private int getLeavesThisMonth(int employeeId) {
		LocalDate today=LocalDate.now();
		LocalDate StartOfMonth=today.withDayOfMonth(1);
		LocalDate endOfMonth= today.withDayOfMonth(today.lengthOfMonth());
		List<LeaveApplication>leaves=leaveRepository.findByEmployeeEmpIdAndStatusAndStartDateBetween(employeeId, LeaveStatus.APPROVED, StartOfMonth, endOfMonth);
	    int daysTaken = 0;
	    for (LeaveApplication leave : leaves) {
	    	daysTaken += getDaysBetween(leave.getStartDate(),leave.getEndDate())+1;
	    }
		return daysTaken ;
	}


	private int calculateDaysApplied(LocalDate startDate, LocalDate endDate) {
		int daysApplied = 0;
	    LocalDate nextDate = startDate;
	    while (!nextDate.isAfter(endDate)) {
	        daysApplied++;
	        nextDate = nextDate.plusDays(1);
	    }
	    return daysApplied;
	}


	@Override
	public void cancelLeave(int employeeId, int Leaveid) throws LeaveManagementSystemException {
		Optional<Employee> optEmployee=	employeeRepository.findById(employeeId);
		if(optEmployee.isPresent()) {
			Optional<LeaveApplication>leave=leaveRepository.findById(Leaveid);
			if(leave.isPresent()) {
				LeaveApplication le=leave.get();
				if(le.getEmployee() != null) {
					int daysApplied= calculateDaysApplied(le.getStartDate(),le.getEndDate());
					Employee employee=optEmployee.get();
					employee.setLeavesAvailable(employee.getLeavesAvailable()-daysApplied);
					employee.setLeaveBalance();
					employeeRepository.save(employee);
					leaveRepository.deleteById(Leaveid);
					}else {
						throw new LeaveManagementSystemException("The Leave with id"+Leaveid+"doesnot belongs to" +employeeId );
					}
				}
		}
   }




	@Override
	public int calculateLeaveBalance(int empId, int Leaveid) {
		Optional<LeaveApplication> optleave= leaveRepository.findById(Leaveid);
		if (optleave.isPresent()) {
	        throw new IllegalArgumentException("Leave not found for leaveId: " + Leaveid);
	    }
	    LeaveApplication leave = optleave.get();
	    if (leave.getStatus() != LeaveStatus.APPROVED) {
	        throw new IllegalStateException("Leave has not been approved yet");
	    }
	    Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
	    if (!optionalEmployee.isPresent()) {
	        throw new IllegalArgumentException("Employee not found for employeeId: " + empId);
	    }
	    Employee employee = optionalEmployee.get();
	    long daysBetween = ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate());
	    int duration = Math.toIntExact(daysBetween) + 1;
	    return employee.getLeavesAvailable() - duration;

	}




	


}
