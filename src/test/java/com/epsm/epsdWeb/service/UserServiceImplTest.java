package com.epsm.epsdWeb.service;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Before;
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
	private User user;
	private ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
	
	@InjectMocks
	private UserServiceImpl service;
	
	@Mock
	private UserDao dao;
	
	@Before
	public void setUp(){
		user = new User();
		user.setName("userName");
		user.setPassword("password");
		user.setEmail("email");
		user.setRole("user");
	}
	
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
		when(dao.findByEmail(anyString())).thenReturn(null);
	}
	
	@Test
	public void DoesNotTryToSaveUserIfItExist(){
		makeUserExist();
		
		service.addNewUser("", "", "");
		
		verify(dao, never()).saveUser(isA(User.class));
	}
	
	private void  makeUserExist(){
		when(dao.findByEmail(anyString())).thenReturn(user);
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
		user = captor.getValue();
		
		Assert.assertEquals("userName", user.getName());
		Assert.assertEquals("password", user.getPassword());
		Assert.assertEquals("email", user.getEmail());
		Assert.assertEquals("user", user.getRole());
	}
}
