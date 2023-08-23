package com.mytimeaway.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mytimeaway.dto.EmployeeLeaveDTO;
import com.mytimeaway.entity.EmployeeLeave;

public class MasterData {

	public static EmployeeLeave getEmployeeLeave() {
		EmployeeLeave employeeLeave = new EmployeeLeave();
		employeeLeave.setId(1L);
		employeeLeave.setEmployeeId("EMP123");
		employeeLeave.setEmployeeName("John Doe");
		employeeLeave.setEmployeePhone("123-456-7890");
		employeeLeave.setEmployeeEmail("john.doe@example.com");
		employeeLeave.setManagerEmail("manager@example.com");
		employeeLeave.setFromDate(new Date());
		employeeLeave.setToDate(new Date());
		employeeLeave.setTotalDays(5);
		employeeLeave.setReason("Vacation");
		employeeLeave.setIsProcessed(false);
		employeeLeave.setStatus("Pending");
		return employeeLeave;
	}

	
	public static EmployeeLeaveDTO getEmployeeLeaveDTO() {
		EmployeeLeaveDTO employeeLeaveDTO = new EmployeeLeaveDTO();
		employeeLeaveDTO.setId(1L);
		employeeLeaveDTO.setEmployeeId("EMP123");
		employeeLeaveDTO.setEmployeeName("John Doe");
		employeeLeaveDTO.setEmployeePhone("123-456-7890");
		employeeLeaveDTO.setEmployeeEmail("john.doe@example.com");
		employeeLeaveDTO.setManagerEmail("manager@example.com");
		employeeLeaveDTO.setFromDate(new Date());
		employeeLeaveDTO.setToDate(new Date());
		employeeLeaveDTO.setTotalDays(5);
		employeeLeaveDTO.setReason("Vacation");
		employeeLeaveDTO.setIsProcessed(false);
		employeeLeaveDTO.setStatus("Pending");
		return employeeLeaveDTO;
	}

	public static List<EmployeeLeaveDTO> getEmployeeLeaveDTOList() {
		List<EmployeeLeaveDTO> employeeLeaveDTOList = new ArrayList<>();
		EmployeeLeaveDTO employeeLeaveDTO1 = getEmployeeLeaveDTO();
		employeeLeaveDTOList.add(employeeLeaveDTO1);

		EmployeeLeaveDTO employeeLeaveDTO2 = new EmployeeLeaveDTO();
		employeeLeaveDTO2.setId(2L);
		employeeLeaveDTO2.setEmployeeId("EMP456");
		employeeLeaveDTO2.setEmployeeName("Jane Smith");
		employeeLeaveDTO2.setEmployeePhone("987-654-3210");
		employeeLeaveDTO2.setEmployeeEmail("jane.smith@example.com");
		employeeLeaveDTO2.setManagerEmail("manager@example.com");
		employeeLeaveDTO2.setFromDate(new Date());
		employeeLeaveDTO2.setToDate(new Date());
		employeeLeaveDTO2.setTotalDays(4);
		employeeLeaveDTO2.setReason("Personal leave");
		employeeLeaveDTO2.setIsProcessed(true);
		employeeLeaveDTO2.setStatus("Approved");
		employeeLeaveDTOList.add(employeeLeaveDTO2);

		return employeeLeaveDTOList;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
