package com.epsm.epsdWeb.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.epsm.epsdWeb.service.chartService.ChartService;

@RunWith(MockitoJUnitRunner.class)
public class HistoryPageControllerTest {
	private MockMvc mockMvc;
	private Map<String, String> map;
	
	@InjectMocks
	private HistoryPageController controller;
	
	@Mock
	private ChartService service;
	
	@Before
	public void setUp() throws Exception{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .build();
		map = new HashMap<String, String>();
		map.put("someParameter", "someValue");
		when(service.getDataForCharts()).thenReturn(map);
		setDispatcherUrl();
	}
	
	private void setDispatcherUrl() throws Exception{
		Field modelUrl = controller.getClass().getDeclaredField("modelUrl");
		modelUrl.setAccessible(true);
		modelUrl.set(controller, "someUrl");
	}
	
	@Test
    public void testCreateHistoryPage() throws Exception {		
        mockMvc.perform(get("/app/history"))
                .andExpect(status().isOk())
                .andExpect(view().name("history"))
                .andExpect(model().attribute("modelUrl", "someUrl"))
                .andExpect(model().attribute("someParameter", "someValue"));

        verify(service).getDataForCharts();
        verifyNoMoreInteractions(service);
	}
}
