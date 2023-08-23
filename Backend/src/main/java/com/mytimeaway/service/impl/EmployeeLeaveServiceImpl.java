package com.mytimeaway.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytimeaway.dto.EmployeeLeaveDTO;
import com.mytimeaway.entity.EmployeeLeave;
import com.mytimeaway.exception.ApplicationNotFoundException;
import com.mytimeaway.repo.EmployeeLeaveRepository;
import com.mytimeaway.service.EmployeeLeaveService;

@Service
public class EmployeeLeaveServiceImpl implements EmployeeLeaveService {

	@Autowired
	private EmployeeLeaveRepository leaveRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<EmployeeLeaveDTO> getAllLeaves() {
		List<EmployeeLeave> leaves = leaveRepository.findAll();
		return leaves.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public EmployeeLeaveDTO getLeaveById(Long id) {
		EmployeeLeave employeeLeave = leaveRepository.findById(id)
				.orElseThrow(() -> new ApplicationNotFoundException("Leave with Id - " + id + " not found!"));
		return modelMapper.map(employeeLeave, EmployeeLeaveDTO.class);
	}

	@Override
	public EmployeeLeaveDTO createLeave(EmployeeLeaveDTO leaveDTO) {
		EmployeeLeave leave = convertToEntity(leaveDTO);
		EmployeeLeave savedLeave = leaveRepository.save(leave);
		return convertToDTO(savedLeave);
	}

	@Override
	public EmployeeLeaveDTO updateLeaveById(Long id, EmployeeLeaveDTO leaveDTO) {
		EmployeeLeave employeeLeave = leaveRepository.findById(id)
				.orElseThrow(() -> new ApplicationNotFoundException("Leave with Id - " + id + " not found!"));
		modelMapper.map(leaveDTO, employeeLeave);
		EmployeeLeave updatedLeave = leaveRepository.save(employeeLeave);
		return modelMapper.map(updatedLeave, EmployeeLeaveDTO.class);
	}

	@Override
	public boolean deleteLeaveById(Long id) {
		Optional<EmployeeLeave> leaveOptional = leaveRepository.findById(id);
		if (leaveOptional.isPresent()) {
			EmployeeLeave employeeLeave = leaveOptional.get();
			leaveRepository.delete(employeeLeave);
			return true;
		} else {
			throw new ApplicationNotFoundException("Leave application not found");
		}
	}

	@Override
	public List<EmployeeLeaveDTO> searchLeaves(String employeeId, String employeeName, Integer totalDays) {
		List<EmployeeLeave> leaves = leaveRepository.findAll();
		return leaves.stream().filter(leave -> employeeId == null || leave.getEmployeeId().equals(employeeId))
				.filter(leave -> employeeName == null || leave.getEmployeeName().equalsIgnoreCase(employeeName))
				.filter(leave -> totalDays <= 0 || leave.getTotalDays() == totalDays).map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeLeaveDTO cancelLeaveRequest(Long id) {
		return changeLeaveStatus(id, "CANCELED");
	}

	@Override
	public EmployeeLeaveDTO approveLeaveRequest(Long id) {
		return changeLeaveStatus(id, "APPROVED");
	}

	@Override
	public EmployeeLeaveDTO rejectLeaveRequest(Long id) {
		return changeLeaveStatus(id, "REJECTED");
	}

	private EmployeeLeaveDTO changeLeaveStatus(Long id, String newStatus) {
		EmployeeLeave employeeLeave = leaveRepository.findById(id)
				.orElseThrow(() -> new ApplicationNotFoundException("Leave with Id - " + id + " not found!"));
		employeeLeave.setStatus(newStatus);
		employeeLeave.setIsProcessed(true);
		EmployeeLeave updatedLeave = leaveRepository.save(employeeLeave);
		return modelMapper.map(updatedLeave, EmployeeLeaveDTO.class);
	}

	private EmployeeLeaveDTO convertToDTO(EmployeeLeave leave) {
		EmployeeLeaveDTO leaveDTO = new EmployeeLeaveDTO();
		BeanUtils.copyProperties(leave, leaveDTO);
		return leaveDTO;
	}

	private EmployeeLeave convertToEntity(EmployeeLeaveDTO leaveDTO) {
		EmployeeLeave leave = new EmployeeLeave();
		BeanUtils.copyProperties(leaveDTO, leave);
		return leave;
	}
}
