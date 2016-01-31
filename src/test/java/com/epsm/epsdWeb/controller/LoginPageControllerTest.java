package com.epsm.epsdWeb.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class LoginPageControllerTest {
	private LoginPageController controller;
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception{
		controller = new LoginPageController();
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .build();
	}
	
	@Test
    public void returnsLoginPageOnBlankRequest() throws Exception {		
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
	}
	
	@Test
    public void returnsLoginPageOnLoginRequest() throws Exception {		
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
	}
}
