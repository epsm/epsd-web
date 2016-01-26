package com.epsm.epsdWeb.repository;

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
public class GeneratorStateDaoImplTest{
	
	@Autowired
	SavedGeneratorStateDao dao;
	
	@Test
	@DatabaseSetup(value="generator_last_entry_date.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testGetLastEntryDate(){
		LocalDate result = dao.getLastEntryDate();
		
		Assert.assertEquals(LocalDate.of(2000, 10, 10), result);
	}
	
	@Test
	@DatabaseSetup(value="generator_states_on_date.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testGetStatesOnDate(){
		LocalDate necessaryDate = LocalDate.of(2000, 9, 8);
		
		List<SavedGeneratorState> result = dao.getStatesOnDate(necessaryDate);
		
		Assert.assertEquals(2, result.size());
	}
	
	@Test
	@DatabaseSetup(value="generator_power_objects_ids_on_day.xml",
			type=DatabaseOperation.CLEAN_INSERT)
	public void testGetPowerObjectsIdsOnDate(){
		LocalDate necessaryDate = LocalDate.of(2000, 9, 8);
		
		List<Long> powerObjectsIds = dao.getPowerObjectsIdsOnDate(necessaryDate);
		
		Assert.assertEquals(2, powerObjectsIds.size());
		Assert.assertTrue(powerObjectsIds.contains(1L));
		Assert.assertTrue(powerObjectsIds.contains(3L));
	}	
	
	@Test
	@DatabaseSetup(value="generator_numbers_on_date_for_power_station.xml",
			type=DatabaseOperation.CLEAN_INSERT)
	public void testGetGeneratorsNumbersOnDateForPowerStation(){
		LocalDate necessaryDate = LocalDate.of(2000, 9, 8);
		long powerObjectId = 88;
		
		List<Integer> generatorNumbers 
			= dao.getGeneratorsNumbersOnDateForPowerStation(necessaryDate, powerObjectId);
		
		Assert.assertEquals(2, generatorNumbers.size());
		Assert.assertTrue(generatorNumbers.contains(2));
		Assert.assertTrue(generatorNumbers.contains(3));
	}
	
	@Test
	@DatabaseSetup(value="generator_states_on_date_for_power_station_and_generator.xml",
			type=DatabaseOperation.CLEAN_INSERT)
	public void testGetGeneratorStatesOnDateForPowerStation(){
		LocalDate necessaryDate = LocalDate.of(2000, 9, 8);
		long powerObjectId = 88;
		int generatorNumber = 2;
		
		List<SavedGeneratorState> generatorsStates 
				= dao.getStatesOnDateForPowerStationAndGenerator(
				necessaryDate, powerObjectId, generatorNumber);
		
		Assert.assertEquals(2, generatorsStates.size());
	}
}
