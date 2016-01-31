package com.epsm.epsdWeb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epsm.epsdWeb.controller.RegistrationPageController;
import com.epsm.epsdWeb.domain.User;
import com.epsm.epsdWeb.repository.UserDao;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao dao;
	
	@Override
	public boolean addNewUser(String userName, String password, String email) {
		List<User> user = getExistUser(email);
		
		if(user.size() != 0){
			logger.debug("Attempt: fail create new user with email {}.", email);
			return false;
		}
		
		saveNewUser(userName, password, email);
		
		logger.debug("Attempt: created new user with email {}.", email);
		return true;
	}
	
	private List<User> getExistUser(String email){
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
		user.setRole("USER");
		
		return user;
	}
	
	private void saveUser(User user){
		dao.saveUser(user);
	}
}
