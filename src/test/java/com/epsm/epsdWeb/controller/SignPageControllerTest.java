package com.epsm.epsdWeb.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.epsm.epsdWeb.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class SignPageControllerTest {
	private MockMvc mockMvc;
	
	@InjectMocks
	private SignPageController controller;
	
	@Mock
	private UserService service;
	
	@Before
	public void setUp() throws Exception{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .build();
	}
	
	@Test
	public void returnsSignPageOnGetRequest() throws Exception{
		mockMvc.perform(get("/"))
        		.andExpect(status().isOk())
        		.andExpect(view().name("sign"));
        		
		verifyNoMoreInteractions(service);
	}
	
	@Test
    public void doNotsaveNewUserIfUserNameIsInvalid() throws Exception {		
		mockMvc.perform(post("/")
				.param("userName", "")
				.param("password", "123456")
				.param("email", "valid@mail.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("sign"))
                .andExpect(model().attributeHasFieldErrors("request","userName"));

		verify(service, never()).addNewUser(any(), any(), any());
		verifyNoMoreInteractions(service);
	}
	
	@Test
    public void doNotsaveNewUserIfPasswordIsInvalid() throws Exception {		
		mockMvc.perform(post("/")
				.param("userName", "123456")
				.param("password", "")
				.param("email", "valid@mail.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("sign"))
                .andExpect(model().attributeHasFieldErrors("request","password"));

		verify(service, never()).addNewUser(any(), any(), any());
		verifyNoMoreInteractions(service);
	}
	
	@Test
    public void doNotsaveNewUserIfEmailIsInvalid() throws Exception {		
		mockMvc.perform(post("/")
				.param("userName", "123456")
				.param("password", "123456")
				.param("email", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("sign"))
                .andExpect(model().attributeHasFieldErrors("request","email"));

		verify(service, never()).addNewUser(any(), any(), any());
		verifyNoMoreInteractions(service);
	}
	
	@Test
    public void showsEmailIsBusyErrorIfNewUserHasExistingEmail() throws Exception {
		
		mockMvc.perform(post("/")
				.param("userName", "123456")
				.param("password", "123456")
				.param("email", "valid@mail.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("sign"))
                .andExpect(model().attributeHasFieldErrors("request","email"));

		verify(service).addNewUser("123456", "123456", "valid@mail.com");
		verifyNoMoreInteractions(service);
	}
	
	@Test
    public void doesNotShowAnyErrorsIfNewUserSaved() throws Exception {		
		makeServiceAnswerTrue();
		
		mockMvc.perform(post("/")
				.param("userName", "123456")
				.param("password", "123456")
				.param("email", "valid@mail.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("sign"))
                .andExpect(model().hasNoErrors());

		verify(service).addNewUser("123456", "123456", "valid@mail.com");
		verifyNoMoreInteractions(service);
	}
	
	private void makeServiceAnswerTrue(){
		when(service.addNewUser(any(), any(), any())).thenReturn(true);
	}
}
