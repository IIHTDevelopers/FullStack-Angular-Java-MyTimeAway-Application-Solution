package com.mytimeaway.boundary;

import static com.mytimeaway.utils.TestUtils.boundaryTestFile;
import static com.mytimeaway.utils.TestUtils.currentTest;
import static com.mytimeaway.utils.TestUtils.testReport;
import static com.mytimeaway.utils.TestUtils.yakshaAssert;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mytimeaway.dto.EmployeeLeaveDTO;
import com.mytimeaway.utils.MasterData;

public class BoundaryTests {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testHibernateValidationForEmployeeLeaveWithInvalidTotalDays() throws Exception {
		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
		leaveDTO.setTotalDays(-1);
		Set<ConstraintViolation<EmployeeLeaveDTO>> violations = validator.validate(leaveDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testHibernateValidationForNullEmployeeId() throws Exception {
		EmployeeLeaveDTO employeeLeave = MasterData.getEmployeeLeaveDTO();
		employeeLeave.setEmployeeId(null);
		Set<ConstraintViolation<EmployeeLeaveDTO>> violations = validator.validate(employeeLeave);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testHibernateValidationForNullEmployeeName() throws Exception {
		EmployeeLeaveDTO employeeLeave = MasterData.getEmployeeLeaveDTO();
		employeeLeave.setEmployeeName(null);
		Set<ConstraintViolation<EmployeeLeaveDTO>> violations = validator.validate(employeeLeave);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testHibernateValidationForNullEmployeePhone() throws Exception {
		EmployeeLeaveDTO employeeLeave = MasterData.getEmployeeLeaveDTO();
		employeeLeave.setEmployeePhone(null);
		Set<ConstraintViolation<EmployeeLeaveDTO>> violations = validator.validate(employeeLeave);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testHibernateValidationForNullEmployeeEmail() throws Exception {
		EmployeeLeaveDTO employeeLeave = MasterData.getEmployeeLeaveDTO();
		employeeLeave.setEmployeeEmail(null);
		Set<ConstraintViolation<EmployeeLeaveDTO>> violations = validator.validate(employeeLeave);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testHibernateValidationForNullManagerEmail() throws Exception {
		EmployeeLeaveDTO employeeLeave = MasterData.getEmployeeLeaveDTO();
		employeeLeave.setManagerEmail(null);
		Set<ConstraintViolation<EmployeeLeaveDTO>> violations = validator.validate(employeeLeave);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testHibernateValidationForNullFromDate() throws Exception {
		EmployeeLeaveDTO employeeLeave = MasterData.getEmployeeLeaveDTO();
		employeeLeave.setFromDate(null);
		Set<ConstraintViolation<EmployeeLeaveDTO>> violations = validator.validate(employeeLeave);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testHibernateValidationForNullToDate() throws Exception {
		EmployeeLeaveDTO employeeLeave = MasterData.getEmployeeLeaveDTO();
		employeeLeave.setToDate(null);
		Set<ConstraintViolation<EmployeeLeaveDTO>> violations = validator.validate(employeeLeave);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testHibernateValidationForNullReason() throws Exception {
		EmployeeLeaveDTO employeeLeave = MasterData.getEmployeeLeaveDTO();
		employeeLeave.setReason(null);
		Set<ConstraintViolation<EmployeeLeaveDTO>> violations = validator.validate(employeeLeave);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
}
