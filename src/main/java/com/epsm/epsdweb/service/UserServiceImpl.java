package com.epsm.epsdweb.service;

import com.epsm.epsdweb.domain.User;
import com.epsm.epsdweb.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao dao;
	
	@Override
	public boolean addNewUser(String userName, String password, String email) {
		List<User> user = dao.findByEmail(email);
		
		if(user.size() != 0){
			logger.debug("User with email: '{}' already exists.", email);
			return false;
		}
		
		saveNewUser(userName, password, email);
		
		logger.debug("New user with email '{}' created.", email);
		return true;
	}
	
	private void saveNewUser(String userName, String password, String email){
		User user = new User();
		user.setName(userName);
		user.setPassword(password);
		user.setEmail(email);
		user.setRole("USER");

		dao.saveUser(user);
	}
}
