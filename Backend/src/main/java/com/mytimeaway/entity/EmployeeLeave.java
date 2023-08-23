package com.mytimeaway.entity;

import javax.persistence.*;
import javax.validation.constraints.Positive;

import java.util.Date;

@Entity
@Table(name = "employee_leave")
public class EmployeeLeave {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "employee_id", nullable = false)
	private String employeeId;

	@Column(name = "employee_name", nullable = false)
	private String employeeName;

	@Column(name = "employee_phone", nullable = false)
	private String employeePhone;

	@Column(name = "employee_email", nullable = false)
	private String employeeEmail;

	@Column(name = "manager_email", nullable = false)
	private String managerEmail;

	@Column(name = "from_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fromDate;

	@Column(name = "to_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date toDate;

	@Column(name = "total_days", nullable = false)
	@Positive(message = "Total days must be a positive value")
	private int totalDays;

	@Column(name = "reason", nullable = false)
	private String reason;

	@Column(name = "is_processed")
	private Boolean isProcessed = false;

	@Column(name = "status")
	private String status = "";

	public EmployeeLeave() {
		super();
	}

	public EmployeeLeave(Long id, String employeeId, String employeeName, String employeePhone, String employeeEmail,
			String managerEmail, Date fromDate, Date toDate, int totalDays, String reason, Boolean isProcessed,
			String status) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeePhone = employeePhone;
		this.employeeEmail = employeeEmail;
		this.managerEmail = managerEmail;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.totalDays = totalDays;
		this.reason = reason;
		this.isProcessed = isProcessed;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(Boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "EmployeeLeave [id=" + id + ", employeeId=" + employeeId + ", employeeName=" + employeeName
				+ ", employeePhone=" + employeePhone + ", employeeEmail=" + employeeEmail + ", managerEmail="
				+ managerEmail + ", fromDate=" + fromDate + ", toDate=" + toDate + ", totalDays=" + totalDays
				+ ", reason=" + reason + ", isProcessed=" + isProcessed + ", status=" + status + "]";
	}

}
