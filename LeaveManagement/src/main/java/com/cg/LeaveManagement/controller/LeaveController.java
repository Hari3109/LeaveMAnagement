package com.cg.LeaveManagement.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.LeaveManagement.Entity.LeaveApplication;
import com.cg.LeaveManagement.Exception.LeaveManagementSystemException;
import com.cg.LeaveManagement.service.LeaveService;

@RestController
public class LeaveController {
 @Autowired
 private LeaveService leaveservice;
      @PostMapping("/employee/{empId}/leave/{startDate}/{endDate}/{reason}/{managerId}")
      public LeaveApplication applyLeave(@PathVariable("empId") Integer empId,@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate,
          @PathVariable("reason") String reason ) throws LeaveManagementSystemException {
            LocalDate start= LocalDate.parse(startDate);
             LocalDate end= LocalDate.parse(endDate);
         return leaveservice.applyLeave(empId, reason, start,end);
            
      }
      @DeleteMapping("/employee/{empId}/Leave/{Leaveid}")
      public void cancelleave(@PathVariable("empId")Integer empId,@PathVariable("Leaveid")Integer Leaveid)throws LeaveManagementSystemException {
    	  leaveservice.cancelLeave(empId,Leaveid);
      }
}
