package com.epsm.epsdWeb.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epsm.epsdWeb.domain.ValueSource;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;


public class FrequencyDaoImplTest extends AbstractDaoTest{
	
	@Autowired
	private FrequencyDao dao;
	
	@Test
	@DatabaseSetup(value="frequency.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testGetTotalGenerations(){
		LocalDate date = LocalDate.of(2000, 10, 10);
		List<ValueSource> result = dao.getFrequencies(date);
		
		Assert.assertEquals(2, result.size());
	}
}
