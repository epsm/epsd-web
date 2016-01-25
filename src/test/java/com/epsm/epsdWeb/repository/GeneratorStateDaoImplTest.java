package com.epsm.epsdWeb.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.epsm.epsdWeb.configuration.DbTestConfiguration;
import com.epsm.epsdWeb.domain.SavedGeneratorState;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DbTestConfiguration.class})
@TestExecutionListeners({DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class,	DirtiesContextTestExecutionListener.class,})
@Transactional
@DatabaseSetup(value="generator_states.xml", type=DatabaseOperation.REFRESH)
public class GeneratorStateDaoImplTest{
	
	@Autowired
	SavedGeneratorStateDao dao;
	
	@Test
	public void testGetLastSaveDate(){
		Date result = dao.getLastSaveDate();
		
		Assert.assertEquals(Date.valueOf(LocalDate.of(2000, 10, 10)), result);
	}
	
	@Test
	public void testLastSaveDate(){
		Date result = dao.getLastSaveDate();
		
		Assert.assertEquals(Date.valueOf(LocalDate.of(2000, 10, 10)), result);
	}
	
	@Test
	public void testGetStatesOnDate(){
		LocalDate necessaryDate = LocalDate.of(2000, 9, 8);
		
		List<SavedGeneratorState> result = dao.getStatesOnDate(necessaryDate);
		
		Assert.assertEquals(3, result.size());
	}
}
