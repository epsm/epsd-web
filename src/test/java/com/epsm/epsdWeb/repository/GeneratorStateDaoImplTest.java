package com.epsm.epsdWeb.repository;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class GeneratorStateDaoImplTest extends AbstractDaoTest{
	
	@Autowired
	private SavedGeneratorStateDao dao;
	
	@Test
	@DatabaseSetup(value="generator_last_entry_date.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testGetLastEntryDate(){
		LocalDate result = dao.getLastEntryDate();
		
		Assert.assertEquals(LocalDate.of(2000, 10, 10), result);
	}
	
	@Test
	@DatabaseSetup(value="generator_last_entry_date.xml", type=DatabaseOperation.DELETE_ALL)
	public void getLastEntryDateReturnsLocalDateMinIfThereIsNotAnyDates(){
		LocalDate result = dao.getLastEntryDate();
		
		Assert.assertEquals(LocalDate.MIN, result);
	}
}
