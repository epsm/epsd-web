package com.epsm.epsdweb.controller;

import com.epsm.epsdweb.service.IncomingMessageService;
import com.epsm.epsmcore.model.consumption.ConsumerState;
import com.epsm.epsmcore.model.consumption.ConsumerType;
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
import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerControllerTest {

	private MockMvc mockMvc;
	private ObjectMapper mapper;
	
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
	public void acceptConsumerStateMethodAcceptsConsumerState() throws Exception {
		List<ConsumerState> states = asList(new ConsumerState(0, LocalDateTime.MIN, 20, ConsumerType.SCHEDULED_LOAD));
		
		mockMvc.perform(
				post("/api/consumer/acceptstate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(states)))
				.andExpect(status().isOk());
	}
}
