package com.cg.LeaveManagement.Entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class Manager {
@Id
private int managerId;
private String ManagerName;

public int getManagerId() {
	return managerId;
}
public void setManagerId(int managerId) {
	this.managerId = managerId;
}
public String getManagerName() {
	return ManagerName;
}
public void setManagerName(String managerName) {
	ManagerName = managerName;
}




public Manager(int managerId, String managerName) {
	super();
	this.managerId = managerId;
	ManagerName = managerName;
}

public Manager() {
	super();
}


}
