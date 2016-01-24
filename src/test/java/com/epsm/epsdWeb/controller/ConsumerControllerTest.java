package com.epsm.epsdWeb.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.epsm.epsdWeb.service.IncomingMessageService;
import com.epsm.epsmCore.model.consumption.ConsumerParametersStub;
import com.epsm.epsmCore.model.consumption.ConsumerState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerControllerTest {
	private MockMvc mockMvc;
	private ObjectMapper mapper;
	private String objectInJsonString;
	private Object objectToSerialize;
	
	@InjectMocks
	private ConsumerController controller;
	
	@Mock
	private IncomingMessageService service;
	
	@Before
	public void setUp(){
		mockMvc = standaloneSetup(controller).build();
		mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
	}
	
	@Test
	public void registeriConsumerMethodReturnsOKIfServiceRegisterConsumer() throws Exception{
		prepareParemetersAsJSONString();
		makeServiceRegisterConsumer();
		performRequestAndWaitOK();
	}
	
	private void prepareParemetersAsJSONString() throws JsonProcessingException{
		objectToSerialize = new ConsumerParametersStub(0, LocalDateTime.MIN, LocalDateTime.MIN);
		objectInJsonString = mapper.writeValueAsString(objectToSerialize);
	}
	
	private void makeServiceRegisterConsumer(){
		when(service.registerConsumer(any())).thenReturn(true);
	}
	
	private void performRequestAndWaitOK() throws Exception{
		mockMvc.perform(
				post("/api/consumer/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectInJsonString))
				.andExpect(status().isOk());
	}
	
	@Test
	public void registerConsumerMethodReturnsCONFLICTIfServiceDoesNotRegisterConsumer()
			throws Exception{
		
		prepareParemetersAsJSONString();
		makeServiceNotToRegisterConsumer();
		performRequestAndWaitCONFLICT();
	}
	
	private void makeServiceNotToRegisterConsumer(){
		when(service.registerConsumer(any())).thenReturn(false);
	}
	
	private void performRequestAndWaitCONFLICT() throws Exception{
		mockMvc.perform(
				post("/api/consumer/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectInJsonString))
				.andExpect(status().isConflict());
	}
	
	@Test
	public void acceptConsumerStateMethodAcceptsConsumerState() throws Exception {
		prepareStateAsJSONString();
		
		mockMvc.perform(
				post("/api/consumer/acceptstate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectInJsonString))
				.andExpect(status().isOk());
	}
	
	private void prepareStateAsJSONString() throws JsonProcessingException{
		objectToSerialize = new ConsumerState(0, LocalDateTime.MIN, LocalDateTime.MIN, 20);
		objectInJsonString = mapper.writeValueAsString(objectToSerialize);
	}
}
