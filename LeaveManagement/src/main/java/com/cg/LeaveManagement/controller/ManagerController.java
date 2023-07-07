package com.cg.LeaveManagement.controller;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.LeaveManagement.Entity.LeaveApplication;
import com.cg.LeaveManagement.Entity.Manager;
import com.cg.LeaveManagement.Exception.JwtTokenMalformedException;
import com.cg.LeaveManagement.Exception.JwtTokenMissingException;
import com.cg.LeaveManagement.Exception.LeaveManagementSystemException;
import com.cg.LeaveManagement.jwt.util.JWTUtils;
import com.cg.LeaveManagement.service.ManagerService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController

public class ManagerController {
 @Autowired
 private ManagerService managerService;
 @PostMapping("/managerlogin")
 public String login(@RequestBody Map<String, String> loginRequest,HttpServletResponse response) throws JwtTokenMalformedException, JwtTokenMissingException {
     String managerUserName = loginRequest.get("managerUserName");
     String password = loginRequest.get("password");
     if (managerService.login(managerUserName, password,response)) {
         return "Login successful";
     } else {
         return "Invalid username or password";
     }
 }
 @PostMapping("/managers")
 public Manager addManager(@RequestBody Manager manager) throws JwtTokenMalformedException, JwtTokenMissingException {
//	 JWTUtils.validateToken(response);
	 return managerService.addManager(manager);
 }
 @GetMapping("/manager/{managerId}")
 public Manager getManagerById(@PathVariable Integer managerId,HttpServletRequest response)throws LeaveManagementSystemException, JwtTokenMalformedException, JwtTokenMissingException{
	 JWTUtils.validateToken(response);
	 return managerService.getManagerById(managerId);
 }
// @GetMapping("/manager{managerId}/leaves")
// public List<LeaveApplication>getPendingLeaves(@PathVariable Integer managerId )throws LeaveManagementSystemException{
//	 return managerService.getPendingLeaves(managerId);
// }
 @PutMapping("/manager/leaves/{leaveId}/{managerId}/approve")
 public LeaveApplication approveLeave(@PathVariable Integer managerId,@PathVariable Integer leaveId,HttpServletRequest response)throws LeaveManagementSystemException, JwtTokenMalformedException, JwtTokenMissingException{
	 JWTUtils.validateToken(response);
	 return managerService.approveLeave(managerId, leaveId);
 }
 @PutMapping("/manager/leaves/{leaveId}/{managerId}/reject")
 public LeaveApplication rejectLeave(@PathVariable Integer managerId,@PathVariable Integer LeaveId,HttpServletRequest response)throws LeaveManagementSystemException, JwtTokenMalformedException, JwtTokenMissingException{
	 JWTUtils.validateToken(response);
	 return managerService.rejectLeave(managerId, LeaveId);
 }
}
