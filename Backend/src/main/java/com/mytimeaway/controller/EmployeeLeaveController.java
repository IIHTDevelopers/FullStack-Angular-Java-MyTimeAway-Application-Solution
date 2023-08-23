package com.mytimeaway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mytimeaway.dto.EmployeeLeaveDTO;
import com.mytimeaway.service.EmployeeLeaveService;

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin
public class EmployeeLeaveController {

	private final EmployeeLeaveService leaveService;

	@Autowired
	public EmployeeLeaveController(EmployeeLeaveService leaveService) {
		this.leaveService = leaveService;
	}

	@GetMapping
	public ResponseEntity<List<EmployeeLeaveDTO>> getAllLeaves() {
		List<EmployeeLeaveDTO> leaves = leaveService.getAllLeaves();
		return new ResponseEntity<>(leaves, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeLeaveDTO> getLeaveById(@PathVariable Long id) {
		EmployeeLeaveDTO leave = leaveService.getLeaveById(id);
		if (leave != null) {
			return new ResponseEntity<>(leave, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<EmployeeLeaveDTO> createLeave(@RequestBody EmployeeLeaveDTO leaveDTO) {
		EmployeeLeaveDTO createdLeave = leaveService.createLeave(leaveDTO);
		return new ResponseEntity<>(createdLeave, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeLeaveDTO> updateLeaveById(@PathVariable Long id,
			@RequestBody EmployeeLeaveDTO leaveDTO) {
		EmployeeLeaveDTO updatedLeave = leaveService.updateLeaveById(id, leaveDTO);
		if (updatedLeave != null) {
			return new ResponseEntity<>(updatedLeave, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLeaveById(@PathVariable Long id) {
		leaveService.deleteLeaveById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/search")
	public ResponseEntity<List<EmployeeLeaveDTO>> searchLeaves(@RequestParam(required = false) String employeeId,
			@RequestParam(required = false) String employeeName, @RequestParam(defaultValue = "0") Integer totalDays) {
		List<EmployeeLeaveDTO> leaves = leaveService.searchLeaves(employeeId, employeeName, totalDays);
		return new ResponseEntity<>(leaves, HttpStatus.OK);
	}

	@PutMapping("/{id}/cancel")
	public ResponseEntity<EmployeeLeaveDTO> cancelLeaveRequest(@PathVariable Long id) {
		EmployeeLeaveDTO canceledLeave = leaveService.cancelLeaveRequest(id);
		if (canceledLeave != null) {
			return new ResponseEntity<>(canceledLeave, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}/approve")
	public ResponseEntity<EmployeeLeaveDTO> approveLeaveRequest(@PathVariable Long id) {
		EmployeeLeaveDTO approvedLeave = leaveService.approveLeaveRequest(id);
		if (approvedLeave != null) {
			return new ResponseEntity<>(approvedLeave, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}/reject")
	public ResponseEntity<EmployeeLeaveDTO> rejectLeaveRequest(@PathVariable Long id) {
		EmployeeLeaveDTO rejectedLeave = leaveService.rejectLeaveRequest(id);
		if (rejectedLeave != null) {
			return new ResponseEntity<>(rejectedLeave, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
