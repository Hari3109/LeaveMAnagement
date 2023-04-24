package com.cg.LeaveManagement.Entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class LeaveApplication {

    @Id
    @GeneratedValue
    private int Leaveid;

   
    private LocalDate startDate;

    private LocalDate endDate;

    private int daysApplied;
    private int daysTaken;
@ManyToOne
@JoinColumn(name="empId")
private Employee employee;
	
	
	public void setEmployee(Employee employee) {
	this.employee = employee;
}
	public Employee getEmployee() {
		return employee;
	}

	public int getLeaveid() {
		return Leaveid;
	}

	public void setLeaveid(int leaveid) {
		Leaveid = leaveid;
	}

	

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getDaysApplied() {
		return daysApplied;
	}

	public void setDaysApplied(int daysApplied) {
		this.daysApplied = daysApplied;
	}
	 @Enumerated(EnumType.STRING)
	    @Column(name = "status")
	    private LeaveStatus status;

	public LeaveStatus getStatus() {
		return status;
	}

	public void setStatus(LeaveStatus status) {
		this.status = status;
	}

	
	public LeaveApplication(int leaveid, LocalDate startDate, LocalDate endDate, int daysApplied, int daysTaken,
			Employee employee, LeaveStatus status) {
		super();
		Leaveid = leaveid;
		this.startDate = startDate;
		this.endDate = endDate;
		this.daysApplied = daysApplied;
		this.daysTaken = daysTaken;
		this.employee = employee;
	
		this.status = status;
	}
	public LeaveApplication() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getDaysTaken() {
		return daysTaken;
	}
	public void setDaysTaken(int daysTaken) {
		this.daysTaken = daysTaken;
	}
	public LeaveApplication(LocalDate startDate, LocalDate endDate, int daysApplied, Employee employee,
			LeaveStatus status) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.daysApplied = daysApplied;
		this.employee = employee;
		this.status = status;
	}
	
	
		public boolean isAuthorized() {
			
			return false;
		}
    
}
