package com.epsm.epsdWeb.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class AvaibleDateDaoImplTest extends AbstractDaoTest{
	@Autowired
	private AvaibleDateDao dao;
	
	@Test
	@DatabaseSetup(value="avaible_date.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testGetDates(){
		List<Date> result = dao.getDates();
		
		Assert.assertTrue(result.contains(Date.valueOf(LocalDate.of(2000, 11, 11))));
		Assert.assertTrue(result.contains(Date.valueOf(LocalDate.of(2000, 12, 12))));
	}
}
