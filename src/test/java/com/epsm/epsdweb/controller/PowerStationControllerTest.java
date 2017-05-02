package com.epsm.epsdweb.controller;

import com.epsm.epsdweb.service.IncomingMessageService;
import com.epsm.epsmcore.model.generation.GeneratorState;
import com.epsm.epsmcore.model.generation.PowerStationState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class PowerStationControllerTest {

	private MockMvc mockMvc;
	private ObjectMapper mapper;
	
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
	public void acceptPowerStationStateMethodAcceptsPowerStationState() throws Exception {
		mockMvc.perform(
				post("/api/powerstation/acceptstate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(prepareStateAsJSONString()))
				.andExpect(status().isOk());
	}
	
	private String prepareStateAsJSONString() throws JsonProcessingException {
		PowerStationState stationState = new PowerStationState(0, LocalDateTime.MIN, 1f);
		GeneratorState generatorState = new GeneratorState(1, 10);
		
		stationState.getStates().put(generatorState.getGeneratorNumber(), generatorState);

		return  mapper.writeValueAsString(asList(stationState));
	}
}
