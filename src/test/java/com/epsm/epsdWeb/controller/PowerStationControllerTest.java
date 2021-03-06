package com.epsm.epsdWeb.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.epsm.epsdWeb.service.IncomingMessageService;
import com.epsm.epsmCore.model.generation.GeneratorParameters;
import com.epsm.epsmCore.model.generation.GeneratorState;
import com.epsm.epsmCore.model.generation.PowerStationParameters;
import com.epsm.epsmCore.model.generation.PowerStationState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class PowerStationControllerTest {
	private MockMvc mockMvc;
	private ObjectMapper mapper;
	private String objectInJsonString;
	private Object objectToSerialize;
	
	@InjectMocks
	private PowerStationController controller;
	
	@Mock
	private IncomingMessageService service;
	
	@Before
	public void setUp(){
		mockMvc = standaloneSetup(controller).build();
		mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
	}
	
	@Test
	public void registerPowerStationReturnsOKifServiceRegistersPowerStation()
			throws Exception {
		
		prepareParemetersAsJSONString();
		makeServiceRegisterPowerStation();
		performRequestAndWaitOK();
	}
	
	private void prepareParemetersAsJSONString() throws JsonProcessingException{
		GeneratorParameters generatorParameters = new GeneratorParameters(1, 10, 5);
		PowerStationParameters stationParameters = new PowerStationParameters(
				0, LocalDateTime.MIN, LocalDateTime.MIN, 1);
		
		stationParameters.addGeneratorParameters(generatorParameters);
		objectToSerialize = stationParameters;
		objectInJsonString = mapper.writeValueAsString(objectToSerialize);
	}
	
	private void makeServiceRegisterPowerStation(){
		when(service.registerPowerStation(any())).thenReturn(true);
	}
	
	private void performRequestAndWaitOK() throws Exception{
		mockMvc.perform(
				post("/api/powerstation/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectInJsonString))
				.andExpect(status().isOk());
	}
	
	@Test
	public void registerPowerStationReturnsCONFLICTifServiceDoesNotRegisterPowerStation()
			throws Exception{
		
		prepareParemetersAsJSONString();
		makeServiceNotToRegisterPowerStation();
		perfotmRequestAndWaitCONFLICT();
	}
	
	private void makeServiceNotToRegisterPowerStation(){
		when(service.registerPowerStation(any())).thenReturn(false);
	}
	
	private void perfotmRequestAndWaitCONFLICT() throws Exception{
		mockMvc.perform(
				post("/api/powerstation/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectInJsonString))
				.andExpect(status().isConflict());
	}
	
	@Test
	public void acceptPowerStationStateMethodAcceptsPowerStationState() throws Exception {
		prepareStateAsJSONString();
		
		mockMvc.perform(
				post("/api/powerstation/acceptstate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectInJsonString))
				.andExpect(status().isOk());
	}
	
	private void prepareStateAsJSONString() throws JsonProcessingException{
		PowerStationState stationState = new PowerStationState(
				0, LocalDateTime.MIN, LocalDateTime.MIN, 1, 1f);
		GeneratorState generatorState = new GeneratorState(1, 10);
		
		stationState.addGeneratorState(generatorState);
		objectToSerialize = stationState;
		objectInJsonString = mapper.writeValueAsString(objectToSerialize);
	}
}
