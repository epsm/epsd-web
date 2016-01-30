package com.epsm.epsdWeb.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epsm.epsdWeb.domain.User;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class UserDaoImplTest extends AbstractDaoTest{
	
	@Autowired
	private UserDao dao;
	
	@Test
	@DatabaseSetup(value="user.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testFindByEmail(){
		String email = "email@two.com";
		User result = dao.findByEmail(email);
		
		Assert.assertEquals("Bill", result.getName());
	}
}
