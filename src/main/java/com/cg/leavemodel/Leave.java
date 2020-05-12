package com.cg.leavemodel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "leavetable")
public class Leave {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int leaveId;
	
	@Column(name = "EmployeeId")
	private int empId;

	@Column(name = "Status")
	private String status;

	@Column(name = "Total")
	private final int total=12;

	@Column(name = "Utilized")
	private int utilized;

	@Column(name = "Balance")
	private int balance;

	@Temporal(value = TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfApplication;

	@Temporal(value = TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfLeave;
	
//	@Min(1)
//	@Max(12)
	@Column(name = "Days")
	private int numberOfDays;

	@Column(name = "Manager")
	private int managerId;
	

	public int getLeaveId() {
		return leaveId;
	}



	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}



	public Leave() {
		super();
	}

	
	
	public Leave(int empId, String status, int utilized, int balance, Date dateOfApplication, Date dateOfLeave,
			int numberOfDays, int managerId) {
		super();
		this.empId = empId;
		this.status = status;
		this.utilized = utilized;
		this.balance = balance;
		this.dateOfApplication = dateOfApplication;
		this.dateOfLeave = dateOfLeave;
		this.numberOfDays = numberOfDays;
		this.managerId = managerId;
	}



	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTotal() {
		return total;
	}

	

	public int getUtilized() {
		return utilized;
	}

	public void setUtilized(int utilized) {
		this.utilized = utilized;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Date getDateOfApplication() {
		return dateOfApplication;
	}

	public void setDateOfApplication(Date dateOfApplication) {
		this.dateOfApplication = dateOfApplication;
		
	}

	public Date getDateOfLeave() {
		return dateOfLeave;
	}

	public void setDateOfLeave(Date dateOfLeave) {
		this.dateOfLeave = dateOfLeave;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	@Override
	public String toString() {
		return "Leave [empId=" + empId + ", status=" + status + ", total=" + total + ", utilized=" + utilized
				+ ", balance=" + balance + ", dateOfApplication=" + dateOfApplication + ", dateOfLeave=" + dateOfLeave
				+ ", numberOfDays=" + numberOfDays + ", managerId=" + managerId + "]";
	}

}
