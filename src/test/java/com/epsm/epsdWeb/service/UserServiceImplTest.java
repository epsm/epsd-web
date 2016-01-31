package com.epsm.epsdWeb.service;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epsm.epsdWeb.domain.User;
import com.epsm.epsdWeb.repository.UserDao;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	private List<User> users = Arrays.asList(new User());
	private ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
	
	@InjectMocks
	private UserServiceImpl service;
	
	@Mock
	private UserDao dao;
	
	@Test
	public void triesToGetUserFromDaoWheAddnewUserCalled(){
		service.addNewUser("", "", "");
		
		verify(dao).findByEmail(anyString());
	}
	
	@Test
	public void triesToSaveUserIfItNotExist(){
		makeUserNotExist();
		
		service.addNewUser("", "", "");
		
		verify(dao).saveUser(isA(User.class));
	}
	
	private void  makeUserNotExist(){
		when(dao.findByEmail(anyString())).thenReturn(Collections.emptyList());
	}
	
	@Test
	public void DoesNotTryToSaveUserIfItExist(){
		makeUserExist();
		
		service.addNewUser("", "", "");
		
		verify(dao, never()).saveUser(isA(User.class));
	}
	
	private void  makeUserExist(){
		when(dao.findByEmail(anyString())).thenReturn(users);
	}
	
	@Test
	public void returnsFalseIfUserExist(){
		makeUserExist();
		
		boolean saved = service.addNewUser("", "", "");
		
		Assert.assertFalse(saved);
	}
	
	@Test
	public void returnsTrueIfUserNotExist(){
		makeUserNotExist();
		
		boolean saved = service.addNewUser("", "", "");
		
		Assert.assertTrue(saved);
	}
	
	@Test
	public void createsUserWithCorrectParameters(){
		makeUserNotExist();
		
		service.addNewUser("userName", "password", "email");
		verify(dao).saveUser(captor.capture());
		User user = captor.getValue();
		
		Assert.assertEquals("userName", user.getName());
		Assert.assertEquals("password", user.getPassword());
		Assert.assertEquals("email", user.getEmail());
		Assert.assertEquals("USER", user.getRole());
	}
}
