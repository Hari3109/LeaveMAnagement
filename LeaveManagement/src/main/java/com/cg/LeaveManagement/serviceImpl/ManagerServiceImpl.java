package com.cg.LeaveManagement.serviceImpl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.LeaveManagement.Entity.LeaveApplication;
import com.cg.LeaveManagement.Entity.LeaveStatus;
import com.cg.LeaveManagement.Entity.Manager;
import com.cg.LeaveManagement.Exception.LeaveManagementSystemException;
import com.cg.LeaveManagement.Repository.LeaveRepository;
import com.cg.LeaveManagement.Repository.ManagerRepository;
import com.cg.LeaveManagement.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService{
	@Autowired
	private LeaveRepository leaveRepository;
@Autowired
private ManagerRepository managerRepository;
	@Override
	public Manager getManagerById(Integer managerId) throws LeaveManagementSystemException {
		
		return managerRepository.findById(managerId).orElseThrow(()->new LeaveManagementSystemException("Manager not found"));
	}

//	@Override
//	public List<LeaveApplication> getPendingLeaves(Integer managerId) throws LeaveManagementSystemException {
//		
//		return leaveRepository.findByManagerIdAndStatus(managerId,LeaveStatus.PENDING) ;
//	}

	

	@Override
	public Manager addManager(Manager manager) {
		
		return managerRepository.save(manager);
	}

	@Override
	public LeaveApplication approveLeave(Integer managerId, Integer leaveId) throws LeaveManagementSystemException {
		Manager manager = managerRepository.findById(managerId)
	            .orElseThrow(() -> new LeaveManagementSystemException("Manager not found"));

	    // Retrieve the leave application object using the leaveId parameter
	    LeaveApplication leaveApplication = leaveRepository.findById(leaveId)
	            .orElseThrow(() -> new LeaveManagementSystemException("Leave application not found"));

	  
	    // Update the status of the leave application to APPROVED
	    leaveApplication.setStatus(LeaveStatus.APPROVED);

	    // Save the updated leave application object
	    leaveRepository.save(leaveApplication);

	    return leaveApplication;
	}
	@Override
	public LeaveApplication rejectLeave(Integer managerId, Integer leaveId) throws LeaveManagementSystemException {
		 // Retrieve the manager object using the managerId parameter
	    Manager manager = managerRepository.findById(managerId)
	            .orElseThrow(() -> new LeaveManagementSystemException("Manager not found"));

	    // Retrieve the leave application object using the leaveId parameter
	    LeaveApplication leaveApplication = leaveRepository.findById(leaveId)
	            .orElseThrow(() -> new LeaveManagementSystemException("Leave application not found"));

	   
	    // Update the status of the leave application to REJECTED
	    leaveApplication.setStatus(LeaveStatus.REJECTED);

	    // Save the updated leave application object
	    leaveRepository.save(leaveApplication);

	    return leaveApplication;
		}

}
