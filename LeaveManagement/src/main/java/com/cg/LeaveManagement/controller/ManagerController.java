package com.cg.LeaveManagement.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.LeaveManagement.Entity.LeaveApplication;
import com.cg.LeaveManagement.Entity.Manager;
import com.cg.LeaveManagement.Exception.LeaveManagementSystemException;
import com.cg.LeaveManagement.service.ManagerService;

@RestController
public class ManagerController {
 @Autowired
 private ManagerService managerService;
 @PostMapping("/managers")
 public Manager addManager(@RequestBody Manager manager) {
	 return managerService.addManager(manager);
 }
 @GetMapping("/manager/{managerId}")
 public Manager getManagerById(@PathVariable Integer managerId)throws LeaveManagementSystemException{
	 return managerService.getManagerById(managerId);
 }
// @GetMapping("/manager{managerId}/leaves")
// public List<LeaveApplication>getPendingLeaves(@PathVariable Integer managerId )throws LeaveManagementSystemException{
//	 return managerService.getPendingLeaves(managerId);
// }
 @PutMapping("/manager/leaves/{leaveId}/{managerId}/approve")
 public LeaveApplication approveLeave(@PathVariable Integer managerId,@PathVariable Integer leaveId)throws LeaveManagementSystemException{
	 return managerService.approveLeave(managerId, leaveId);
 }
 @PutMapping("/manager/leaves/{leaveId}/{managerId}/reject")
 public LeaveApplication rejectLeave(@PathVariable Integer managerId,@PathVariable Integer LeaveId)throws LeaveManagementSystemException{
	 return managerService.rejectLeave(managerId, LeaveId);
 }
}
