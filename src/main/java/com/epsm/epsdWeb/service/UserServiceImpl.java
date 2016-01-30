package com.epsm.epsdWeb.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public boolean addNewUser(String userName, String password, String email) {
		String message = String.format("UserServiceImpl: addNewUser("
				+ "userName: %s, password: %s, email: %s) called", userName, password, email);
		System.out.println(message);
		
		return true;
	}
}
