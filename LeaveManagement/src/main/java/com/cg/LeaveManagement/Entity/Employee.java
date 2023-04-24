package com.cg.LeaveManagement.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private int empId;
    private String empName;
    private int leavesAvailable;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getLeavesAvailable() {
		return leavesAvailable;
	}
	public void setLeavesAvailable(int leavesAvailable) {
		this.leavesAvailable = leavesAvailable;
	}
	public int getLeave() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int setLeaveBalance() {
		// TODO Auto-generated method stub
		return getLeavesAvailable();
	}
	
}
