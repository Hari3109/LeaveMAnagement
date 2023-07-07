package com.cg.LeaveManagement.service;

import java.time.LocalDate;

import com.cg.LeaveManagement.Entity.LeaveApplication;
import com.cg.LeaveManagement.Exception.LeaveManagementSystemException;

public interface LeaveService {
	public int calculateLeaveBalance(int empId, int Leaveid);
    public LeaveApplication applyLeave(int employeeId, String name, LocalDate startDate, LocalDate endDate)throws LeaveManagementSystemException;
    public void cancelLeave(int employeeId,int Leaveid)throws LeaveManagementSystemException;
}
