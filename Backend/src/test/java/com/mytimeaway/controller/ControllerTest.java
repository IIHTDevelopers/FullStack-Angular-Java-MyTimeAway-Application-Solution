package com.mytimeaway.controller;

import static com.mytimeaway.utils.TestUtils.asJsonString;
import static com.mytimeaway.utils.TestUtils.businessTestFile;
import static com.mytimeaway.utils.TestUtils.currentTest;
import static com.mytimeaway.utils.TestUtils.testReport;
import static com.mytimeaway.utils.TestUtils.yakshaAssert;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytimeaway.MyTimeAwayApplication;
import com.mytimeaway.dto.EmployeeLeaveDTO;
import com.mytimeaway.service.EmployeeLeaveService;
import com.mytimeaway.utils.MasterData;

@WebMvcTest(EmployeeLeaveController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = MyTimeAwayApplication.class)
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private EmployeeLeaveService leaveService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	void testRestEndpointForFindLeaveByIdIsExposedAndWorking() throws Exception {
		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
		Long id = leaveDTO.getId();
		Mockito.when(leaveService.getLeaveById(id)).thenReturn(leaveDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/leaves/" + id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedResponse = objectMapper.writeValueAsString(leaveDTO);
		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(expectedResponse),
				businessTestFile);
	}

	@Test
	void testRestEndpointForFindLeaveByIdWithNonExistentId() throws Exception {
		Long id = 100L;
		Mockito.when(leaveService.getLeaveById(id)).thenReturn(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/leaves/" + id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404, businessTestFile);
	}

	@Test
	void testRestEndpointForCreatingNewLeaveIsExposedAndWorking() throws Exception {
		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
		Mockito.when(leaveService.createLeave(Mockito.any(EmployeeLeaveDTO.class))).thenReturn(leaveDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/leaves").content(asJsonString(leaveDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedResponse = objectMapper.writeValueAsString(leaveDTO);
		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(expectedResponse),
				businessTestFile);
	}

	@Test
	void testRestEndpointForUpdatingLeaveByIdIsExposedAndWorking() throws Exception {
		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
		Long id = leaveDTO.getId();
		leaveDTO.setReason("Updated Reason");
		Mockito.when(leaveService.updateLeaveById(Mockito.eq(id), Mockito.any(EmployeeLeaveDTO.class)))
				.thenReturn(leaveDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/leaves/" + id).content(asJsonString(leaveDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedResponse = objectMapper.writeValueAsString(leaveDTO);
		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(expectedResponse),
				businessTestFile);
	}

	@Test
	void testRestEndpointForDeletingLeaveByIdIsExposedAndWorking() throws Exception {
		Long id = 1L;
		Mockito.when(leaveService.deleteLeaveById(id)).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/leaves/" + id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 204, businessTestFile);
	}

	@Test
	void testRestEndpointForCancellingLeaveRequestIsExposedAndWorking() throws Exception {
		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
		Long id = leaveDTO.getId();
		Mockito.when(leaveService.cancelLeaveRequest(id)).thenReturn(leaveDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/leaves/" + id + "/cancel")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedResponse = objectMapper.writeValueAsString(leaveDTO);
		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(expectedResponse),
				businessTestFile);
	}

	@Test
	void testRestEndpointForApprovingLeaveRequestIsExposedAndWorking() throws Exception {
		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
		Long id = leaveDTO.getId();
		Mockito.when(leaveService.approveLeaveRequest(id)).thenReturn(leaveDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/leaves/" + id + "/approve")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedResponse = objectMapper.writeValueAsString(leaveDTO);
		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(expectedResponse),
				businessTestFile);
	}

	@Test
	void testRestEndpointForRejectingLeaveRequestIsExposedAndWorking() throws Exception {
		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
		Long id = leaveDTO.getId();
		Mockito.when(leaveService.rejectLeaveRequest(id)).thenReturn(leaveDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/leaves/" + id + "/reject")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedResponse = objectMapper.writeValueAsString(leaveDTO);
		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(expectedResponse),
				businessTestFile);
	}

	@Test
	void testRestEndpointForSearchingLeavesIsExposedAndWorking() throws Exception {
		List<EmployeeLeaveDTO> leaveDTOs = MasterData.getEmployeeLeaveDTOList();
		Mockito.when(leaveService.searchLeaves(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()))
				.thenReturn(leaveDTOs);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/leaves/search").param("employeeId", "123")
				.param("employeeName", "John").param("totalDays", "5").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedResponse = objectMapper.writeValueAsString(leaveDTOs);
		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(expectedResponse),
				businessTestFile);
	}

//	@Test
//	void testRestEndpointForFindLeaveByIdIsExposedAndWorking() throws Exception {
//		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
//		Long id = leaveDTO.getId();
//		Mockito.when(leaveService.getLeaveById(id)).thenReturn(leaveDTO);
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/leaves/" + id)
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		String expectedResponse = objectMapper.writeValueAsString(leaveDTO);
//		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(expectedResponse),
//				businessTestFile);
//	}
//
//	@Test
//	void testRestEndpointForFindLeaveByIdWithNonExistentId() throws Exception {
//		Long id = 100L;
//		Mockito.when(leaveService.getLeaveById(id)).thenReturn(null);
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/leaves/" + id)
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404, businessTestFile);
//	}
//
//	@Test
//	void testRestEndpointForCreatingNewLeaveIsExposedAndWorking() throws Exception {
//		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
//		Mockito.when(leaveService.createLeave(Mockito.any(EmployeeLeaveDTO.class))).thenReturn(leaveDTO);
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/leaves").content(asJsonString(leaveDTO))
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		String expectedResponse = objectMapper.writeValueAsString(leaveDTO);
//		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(expectedResponse),
//				businessTestFile);
//	}
//
//	@Test
//	void testRestEndpointForUpdatingLeaveByIdIsExposedAndWorking() throws Exception {
//		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
//		Long id = leaveDTO.getId();
//		leaveDTO.setReason("Updated Reason");
//		Mockito.when(leaveService.updateLeaveById(Mockito.eq(id), Mockito.any(EmployeeLeaveDTO.class)))
//				.thenReturn(leaveDTO);
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/leaves/" + id).content(asJsonString(leaveDTO))
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		String expectedResponse = objectMapper.writeValueAsString(leaveDTO);
//		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(expectedResponse),
//				businessTestFile);
//	}
//
//	@Test
//	void testRestEndpointForDeletingLeaveByIdIsExposedAndWorking() throws Exception {
//		Long id = 1L;
//		Mockito.when(leaveService.deleteLeaveById(id)).thenReturn(true);
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/leaves/" + id)
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		yakshaAssert(currentTest(), result.getResponse().getStatus() == 204, businessTestFile);
//	}
//
//	@Test
//	void testRestEndpointForCancellingLeaveRequestIsExposedAndWorking() throws Exception {
//		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
//		Long id = leaveDTO.getId();
//		Mockito.when(leaveService.cancelLeaveRequest(id)).thenReturn(leaveDTO);
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/leaves/" + id + "/cancel")
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		String expectedResponse = objectMapper.writeValueAsString(leaveDTO);
//		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(expectedResponse),
//				businessTestFile);
//	}
//
//	@Test
//	void testRestEndpointForApprovingLeaveRequestIsExposedAndWorking() throws Exception {
//		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
//		Long id = leaveDTO.getId();
//		Mockito.when(leaveService.approveLeaveRequest(id)).thenReturn(leaveDTO);
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/leaves/" + id + "/approve")
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		String expectedResponse = objectMapper.writeValueAsString(leaveDTO);
//		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(expectedResponse),
//				businessTestFile);
//	}
//
//	@Test
//	void testRestEndpointForRejectingLeaveRequestIsExposedAndWorking() throws Exception {
//		EmployeeLeaveDTO leaveDTO = MasterData.getEmployeeLeaveDTO();
//		Long id = leaveDTO.getId();
//		Mockito.when(leaveService.rejectLeaveRequest(id)).thenReturn(leaveDTO);
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/leaves/" + id + "/reject")
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		String expectedResponse = objectMapper.writeValueAsString(leaveDTO);
//		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(expectedResponse),
//				businessTestFile);
//	}
//
//	@Test
//	void testRestEndpointForSearchingLeavesIsExposedAndWorking() throws Exception {
//		List<EmployeeLeaveDTO> leaveDTOs = MasterData.getEmployeeLeaveDTOList();
//		Mockito.when(leaveService.searchLeaves(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()))
//				.thenReturn(leaveDTOs);
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/leaves/search").param("employeeId", "123")
//				.param("employeeName", "John").param("totalDays", "5").contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		String expectedResponse = objectMapper.writeValueAsString(leaveDTOs);
//		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(expectedResponse),
//				businessTestFile);
//	}

}
