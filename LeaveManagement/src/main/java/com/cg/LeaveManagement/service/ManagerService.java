package com.cg.LeaveManagement.service;

import javax.servlet.http.HttpServletResponse;

import com.cg.LeaveManagement.Entity.LeaveApplication;
import com.cg.LeaveManagement.Entity.Manager;
import com.cg.LeaveManagement.Exception.JwtTokenMalformedException;
import com.cg.LeaveManagement.Exception.JwtTokenMissingException;
import com.cg.LeaveManagement.Exception.LeaveManagementSystemException;

public interface ManagerService {

	public Boolean login(String ManagerUserName, String password, HttpServletResponse response) throws JwtTokenMalformedException, JwtTokenMissingException;

	public Manager addManager(Manager manager);

	public Manager getManagerById(Integer managerId) throws LeaveManagementSystemException;

//    public List<LeaveApplication> getPendingLeaves(Integer managerId) throws LeaveManagementSystemException;
	public LeaveApplication approveLeave(Integer managerId, Integer leaveId) throws LeaveManagementSystemException;

	public LeaveApplication rejectLeave(Integer managerId, Integer leaveId) throws LeaveManagementSystemException;
	
}
