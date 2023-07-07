package com.cg.LeaveManagement.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Integer empId;
    private String empName;
    private String userName;
    private String password;
    private int leavesAvailable;
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
