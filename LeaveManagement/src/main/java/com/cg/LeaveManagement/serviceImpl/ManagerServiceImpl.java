package com.cg.LeaveManagement.serviceImpl;



import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.LeaveManagement.Entity.LeaveApplication;
import com.cg.LeaveManagement.Entity.LeaveStatus;
import com.cg.LeaveManagement.Entity.Manager;

import com.cg.LeaveManagement.Exception.LeaveManagementSystemException;
import com.cg.LeaveManagement.Repository.LeaveRepository;
import com.cg.LeaveManagement.Repository.ManagerRepository;
import com.cg.LeaveManagement.jwt.util.JWTUtils;
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

	   
	    LeaveApplication leaveApplication = leaveRepository.findById(leaveId)
	            .orElseThrow(() -> new LeaveManagementSystemException("Leave application not found"));


	    
	    leaveApplication.setStatus(LeaveStatus.APPROVED);

	   
	    leaveRepository.save(leaveApplication);

	    return leaveApplication;
	}
	@Override
	public LeaveApplication rejectLeave(Integer managerId, Integer leaveId) throws LeaveManagementSystemException {
		
	    Manager manager = managerRepository.findById(managerId)
	            .orElseThrow(() -> new LeaveManagementSystemException("Manager not found"));

	   
	    LeaveApplication leaveApplication = leaveRepository.findById(leaveId)
	            .orElseThrow(() -> new LeaveManagementSystemException("Leave application not found"));


	  
	    leaveApplication.setStatus(LeaveStatus.REJECTED);

	   
	    leaveRepository.save(leaveApplication);

	    return leaveApplication;
	}

	@Override
	public Boolean login(String ManagerUserName, String password, HttpServletResponse response) {

		if (ManagerUserName == null || ManagerUserName.trim().isEmpty()) {
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
	Manager manager = managerRepository.findBymanagerUserName(ManagerUserName);
    if (manager == null) {
        return false;
    }

    String token=JWTUtils.generateToken(manager.getManagerId().toString());
    response.setHeader("Authorization", token);
    response.addHeader("token", token);
    response.addHeader("Access-Control-Expose-Headers", "token");
    return manager.getPassword().equals(password);

	}
}
//	@Override
//	public String login(String managerUserName, String password, HttpServletResponse response) {
//		if (isValid(managerUserName, password)) {
////	        String token = Jwts.builder()
////	                .setSubject(managerUserName)
////	                .setIssuedAt(new Date())
////	                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
////	                .signWith(SignatureAlgorithm.HS256, "secretKey")
////	                .compact();
////	        Cookie cookie = new Cookie("token", token);
////	        response.addCookie(cookie);
//
////	        return token;
//	    } else {
//	        return null;
//	    }
//	}
//
//	private boolean isValid(String managerUserName, String password) {
//		if (managerUserName != null && !managerUserName.isEmpty() && password != null && !password.isEmpty())
//		{ if (managerUserName.equals(managerUserName) && password.equals(password)) {
//
//		return true;
//
//		}
//
//		}
//
//		return false;
//	}



