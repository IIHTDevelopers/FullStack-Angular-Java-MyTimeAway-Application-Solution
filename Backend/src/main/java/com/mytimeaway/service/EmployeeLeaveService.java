package com.mytimeaway.service;

import java.util.List;

import com.mytimeaway.dto.EmployeeLeaveDTO;

public interface EmployeeLeaveService {
	List<EmployeeLeaveDTO> getAllLeaves();

	EmployeeLeaveDTO getLeaveById(Long id);

	EmployeeLeaveDTO createLeave(EmployeeLeaveDTO leaveDTO);

	EmployeeLeaveDTO updateLeaveById(Long id, EmployeeLeaveDTO leaveDTO);

	boolean deleteLeaveById(Long id);

	List<EmployeeLeaveDTO> searchLeaves(String employeeId, String employeeName, Integer totalDays);

	EmployeeLeaveDTO cancelLeaveRequest(Long id);

	EmployeeLeaveDTO approveLeaveRequest(Long id);

	EmployeeLeaveDTO rejectLeaveRequest(Long id);
}
