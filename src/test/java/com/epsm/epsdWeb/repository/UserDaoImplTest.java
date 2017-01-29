package com.epsm.epsdWeb.repository;

import java.util.List;

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
	@DatabaseSetup(value="users.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testFindByEmail(){
		String email = "email@two.com";
		List<User> result = dao.findByEmail(email);
		
		Assert.assertEquals(1, result.size());
	}
}
