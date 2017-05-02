package com.epsm.epsdweb.controller;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.epsm.epsdweb.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationPageControllerTest {
	private MockMvc mockMvc;
	private ArgumentCaptor<String> captor =ArgumentCaptor.forClass(String.class);
	
	@InjectMocks
	private RegistrationPageController controller;
	
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
	public void returnsRegistrationPageOnGetRequest() throws Exception{
		mockMvc.perform(get("/registration"))
        		.andExpect(status().isOk())
        		.andExpect(view().name("registration"));
        		
		verifyNoMoreInteractions(service);
	}
	
	@Test
    public void doesNotTryToSaveNewUserIfUserNameIsInvalidAndShowError() throws Exception {		
		mockMvc.perform(post("/registration")
				.param("userName", "")
				.param("password", "123456")
				.param("email", "valid@mail.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(model().attributeHasFieldErrors("request","userName"));

		verify(service, never()).addNewUser(any(), any(), any());
		verifyNoMoreInteractions(service);
	}
	
	@Test
    public void doesNotTrySaveNewUserIfPasswordIsInvalidAndShowError() throws Exception {		
		mockMvc.perform(post("/registration")
				.param("userName", "123456")
				.param("password", "")
				.param("email", "valid@mail.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(model().attributeHasFieldErrors("request","password"));

		verify(service, never()).addNewUser(any(), any(), any());
		verifyNoMoreInteractions(service);
	}
	
	@Test
    public void doesNotTrySaveNewUserIfEmailIsInvalidAndShowError() throws Exception {		
		mockMvc.perform(post("/registration")
				.param("userName", "123456")
				.param("password", "123456")
				.param("email", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(model().attributeHasFieldErrors("request","email"));

		verify(service, never()).addNewUser(any(), any(), any());
		verifyNoMoreInteractions(service);
	}
	
	@Test
    public void showsEmailIsBusyErrorIfNewUserHasExistingEmail() throws Exception {
		mockMvc.perform(post("/registration")
				.param("userName", "123456")
				.param("password", "123456")
				.param("email", "valid@mail.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(model().attributeHasFieldErrors("request","email"));

		verify(service).addNewUser(eq("123456"), anyString(), eq("valid@mail.com"));
		verifyNoMoreInteractions(service);
	}
	
	@Test
    public void redirectsOnLoginPageIfNewUserRegistered() throws Exception {		
		makeServiceAnswerTrue();
		
		mockMvc.perform(post("/registration")
				.param("userName", "123456")
				.param("password", "123456")
				.param("email", "valid@mail.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));

		verify(service).addNewUser(eq("123456"), anyString(), eq("valid@mail.com"));
		verifyNoMoreInteractions(service);
	}
	
	private void makeServiceAnswerTrue(){
		when(service.addNewUser(any(), any(), any())).thenReturn(true);
	}
	
	@Test
    public void encodesUserPassword() throws Exception {		
		mockMvc.perform(post("/registration")
				.param("userName", "123456")
				.param("password", "123456")
				.param("email", "valid@mail.com"))
                .andExpect(status().isOk());

		verify(service).addNewUser(eq("123456"), captor.capture(), eq("valid@mail.com"));
		Assert.assertTrue(isPasswordEncodedCorrect());
	}
	
	private boolean isPasswordEncodedCorrect(){
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		
		return encoder.matches("123456", captor.getValue());
	}
}
