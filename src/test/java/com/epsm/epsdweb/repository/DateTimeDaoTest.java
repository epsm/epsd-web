package com.epsm.epsdweb.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class DateTimeDaoTest extends AbstractDaoTest {
	
	@Autowired
	private DateTimeDao dao;
	
	@Test
	public void testFindByEmail(){
		assertEquals(LocalDateTime.of(2001, 1, 1, 0, 0, 0), dao.getLastDate());
	}
}
