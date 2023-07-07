package com.cg.LeaveManagement.Entity;





import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;


@Entity
public class Manager {
@Id
private Integer managerId;
private String managerName;
private String managerUserName;
private String password;
public Integer getManagerId() {
	return managerId;
}
public void setManagerId(Integer managerId) {
	this.managerId = managerId;
}
public String getManagerName() {
	return managerName;
}
public void setManagerName(String managerName) {
	this.managerName = managerName;
}
public String getManagerUserName() {
	return managerUserName;
}
public void setManagerUserName(String managerUserName) {
	this.managerUserName = managerUserName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Manager(int managerId, String managerName) {
	super();
	this.managerId = managerId;
	this.managerName = managerName;
}

public Manager() {
	super();
}

}
