package com.epsm.epsdWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epsm.epsdWeb.domain.User;
import com.epsm.epsdWeb.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;
	
	@Override
	public boolean addNewUser(String userName, String password, String email) {
		User user = getExistUser(email);
		
		if(user != null){
			return false;
		}
		
		saveNewUser(userName, password, email);
		
		return true;
	}
	
	private User getExistUser(String email){
		return dao.findByEmail(email);
	}
	
	private void saveNewUser(String userName, String password, String email){
		User user = createUser(userName, password, email);
		saveUser(user);
	}
	
	private User createUser(String userName, String password, String email){
		User user = new User();
		user.setName(userName);
		user.setPassword(password);
		user.setEmail(email);
		user.setRole("user");
		
		return user;
	}
	
	private void saveUser(User user){
		dao.saveUser(user);
	}
}
