package com.epsm.epsdweb.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epsm.epsdweb.domain.User;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class UserDaoTest extends AbstractDaoTest {
	
	@Autowired
	private UserDao dao;
	
	@Test
	public void testFindByEmail(){
		String email = "email@two.com";
		List<User> result = dao.findByEmail(email);
		
		Assert.assertEquals(1, result.size());
	}
}