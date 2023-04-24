package com.cg.LeaveManagement.service;

import java.util.List;

import com.cg.LeaveManagement.Entity.LeaveApplication;
import com.cg.LeaveManagement.Entity.Manager;
import com.cg.LeaveManagement.Exception.LeaveManagementSystemException;

public interface ManagerService {
	public Manager addManager(Manager manager);
    public Manager getManagerById(Integer managerId) throws LeaveManagementSystemException;
//    public List<LeaveApplication> getPendingLeaves(Integer managerId) throws LeaveManagementSystemException;
    public LeaveApplication approveLeave(Integer managerId, Integer leaveId) throws LeaveManagementSystemException;
    public LeaveApplication rejectLeave(Integer managerId, Integer leaveId) throws LeaveManagementSystemException;
}

