package com.mytimeaway.exception;

import static com.mytimeaway.utils.TestUtils.currentTest;
import static com.mytimeaway.utils.TestUtils.exceptionTestFile;
import static com.mytimeaway.utils.TestUtils.testReport;
import static com.mytimeaway.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mytimeaway.controller.EmployeeLeaveController;
import com.mytimeaway.dto.EmployeeLeaveDTO;
import com.mytimeaway.service.EmployeeLeaveService;
import com.mytimeaway.utils.MasterData;

@WebMvcTest(EmployeeLeaveController.class)
public class ExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeLeaveService leaveService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}
//	@Test
//	public void testExceptionIsThrownAndHandledIfLeaveIdIsNotValidWhileGettingLeaveById() throws Exception {
//		Long id = 211L;
//		when(leaveService.getLeaveById(id))
//				.thenThrow(new ApplicationNotFoundException("Leave with Id - " + id + " not found!"));
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/leaves/" + id)
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		result.getResponse().getContentAsString().contains("Leave with Id - " + id + " not found!");
//		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value() ? true : false,
//				exceptionTestFile);
//	}
//
//	@Test
//	public void testExceptionIsThrownAndHandledIfLeaveIdIsNotValidWhileUpdatingLeave() throws Exception {
//		Long id = 211L;
//		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
//		when(leaveService.updateLeaveById(anyLong(), Mockito.any(EmployeeLeaveDTO.class)))
//				.thenThrow(new ApplicationNotFoundException("Leave with Id - " + id + " not found!"));
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/leaves/" + id)
//				.content(MasterData.asJsonString(leaveDTO)).contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		result.getResponse().getContentAsString().contains("Leave with Id - " + id + " not found!");
//		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value() ? true : false,
//				exceptionTestFile);
//	}
//
//	@Test
//	public void testExceptionIsThrownAndHandledIfLeaveIdIsNotValidWhileDeletingLeave() throws Exception {
//		Long id = 211L;
//		when(leaveService.deleteLeaveById(id))
//				.thenThrow(new ApplicationNotFoundException("Leave with Id - " + id + " not found!"));
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/leaves/" + id)
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		result.getResponse().getContentAsString().contains("Leave with Id - " + id + " not found!");
//		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value() ? true : false,
//				exceptionTestFile);
//	}
//
//	@Test
//	public void testExceptionIsThrownAndHandledIfLeaveIdIsNotValidWhileCancellingLeave() throws Exception {
//		Long id = 211L;
//		when(leaveService.cancelLeaveRequest(id))
//				.thenThrow(new ApplicationNotFoundException("Leave with Id - " + id + " not found!"));
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/leaves/" + id + "/cancel")
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		result.getResponse().getContentAsString().contains("Leave with Id - " + id + " not found!");
//		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value() ? true : false,
//				exceptionTestFile);
//	}
//
//	@Test
//	public void testExceptionIsThrownAndHandledIfLeaveIdIsNotValidWhileApprovingLeave() throws Exception {
//		Long id = 211L;
//		when(leaveService.approveLeaveRequest(id))
//				.thenThrow(new ApplicationNotFoundException("Leave with Id - " + id + " not found!"));
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/leaves/" + id + "/approve")
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		result.getResponse().getContentAsString().contains("Leave with Id - " + id + " not found!");
//		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value() ? true : false,
//				exceptionTestFile);
//	}
//
//	@Test
//	public void testExceptionIsThrownAndHandledIfLeaveIdIsNotValidWhileRejectingLeave() throws Exception {
//		Long id = 211L;
//		when(leaveService.rejectLeaveRequest(id))
//				.thenThrow(new ApplicationNotFoundException("Leave with Id - " + id + " not found!"));
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/leaves/" + id + "/reject")
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		result.getResponse().getContentAsString().contains("Leave with Id - " + id + " not found!");
//		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value() ? true : false,
//				exceptionTestFile);
//	}

	@Test
	public void testCreateLeave() throws Exception {
		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
		when(leaveService.createLeave(any(EmployeeLeaveDTO.class))).thenReturn(leaveDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/leaves")
				.content(MasterData.asJsonString(leaveDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.CREATED.value() ? true : false,
				exceptionTestFile);
	}

	@Test
	public void testCancelLeaveRequest() throws Exception {
		Long id = 1L;
		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
		when(leaveService.cancelLeaveRequest(id)).thenReturn(leaveDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/leaves/" + id + "/cancel")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.OK.value() ? true : false,
				exceptionTestFile);
	}

	@Test
	public void testApproveLeaveRequest() throws Exception {
		Long id = 1L;
		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
		when(leaveService.approveLeaveRequest(id)).thenReturn(leaveDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/leaves/" + id + "/approve")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.OK.value() ? true : false,
				exceptionTestFile);
	}

	@Test
	public void testRejectLeaveRequest() throws Exception {
		Long id = 1L;
		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
		when(leaveService.rejectLeaveRequest(id)).thenReturn(leaveDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/leaves/" + id + "/reject")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.OK.value() ? true : false,
				exceptionTestFile);
	}

	@Test
	public void testGetLeaveById() throws Exception {
		Long id = 1L;
		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
		when(leaveService.getLeaveById(id)).thenReturn(leaveDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/leaves/" + id)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.OK.value() ? true : false,
				exceptionTestFile);
	}

	@Test
	public void testGet404OnInvalidUpdateLeaveById() throws Exception {
		Long id = 1L;
		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
		Mockito.when(leaveService.updateLeaveById(id, leaveDTO)).thenReturn(leaveDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/leaves/" + id)
				.content(MasterData.asJsonString(leaveDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value(), exceptionTestFile);
	}
}
