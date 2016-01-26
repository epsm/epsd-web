package com.epsm.epsdWeb.service.chartDataService;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epsm.epsdWeb.domain.SavedGeneratorState;
import com.epsm.epsdWeb.repository.SavedGeneratorStateDao;

@RunWith(MockitoJUnitRunner.class)
public class DayDateValidatorTest {
	private LocalDate testDate;
	
	@InjectMocks
	private DayDateValidator verifier;
	
	@Mock
	private SavedGeneratorStateDao dao;
	
	@Before
	public void setUp(){
		testDate = LocalDate.of(2000, 10, 20);
		
		when(dao.getPowerObjectsIdsOnDate(testDate)).thenReturn(Arrays.asList(1L, 2L));
		when(dao.getGeneratorsNumbersOnDateForPowerStation(eq(testDate), anyInt())).thenReturn(Arrays.asList(3, 4));
		when(dao.getStatesOnDateForPowerStationAndGenerator(eq(testDate), eq(1L), anyInt())).thenReturn(Collections.emptyList());
		when(dao.getStatesOnDateForPowerStationAndGenerator(eq(testDate), eq(2L), eq(3))).thenReturn(Collections.emptyList());
	}
	
	@Test
	public void isDataOnDateFullMethodReturnsTrueIfAllDataPresence(){
		prepareRightData();
		
		Assert.assertTrue(verifier.isDataOnDateFull(testDate));
	}
	
	private void prepareRightData(){
		when(dao.getStatesOnDateForPowerStationAndGenerator(eq(testDate), eq(2L), eq(4))).thenReturn(getRightStates());
	}
	
	private List<SavedGeneratorState> getRightStates(){
		List<SavedGeneratorState> states = new ArrayList<SavedGeneratorState>();
		LocalTime pointer = LocalTime.MIDNIGHT;
		
		do{
			SavedGeneratorState generatorState = new SavedGeneratorState();
			generatorState.setPowerObjectTime(Time.valueOf(pointer));
			states.add(generatorState);
			
			pointer = pointer.plusMinutes(10);
		}while(pointer.isAfter(LocalTime.MIDNIGHT));
		
		return states;
	}
	
	@Test
	public void isDataOnDateFullMethodReturnsFalseIfNotAllDataPresence(){
		prepareWrongData();
		
		Assert.assertFalse(verifier.isDataOnDateFull(testDate));
	}
	
	private void prepareWrongData(){
		when(dao.getStatesOnDateForPowerStationAndGenerator(eq(testDate), eq(2L), eq(4))).thenReturn(getNotAllStates());
	}
	
	private List<SavedGeneratorState> getNotAllStates(){
		List<SavedGeneratorState> states = new ArrayList<SavedGeneratorState>();
		LocalTime pointer = LocalTime.MIDNIGHT;
		
		do{
			SavedGeneratorState generatorState = new SavedGeneratorState();
			generatorState.setPowerObjectTime(Time.valueOf(pointer));
			states.add(generatorState);
			
			pointer = pointer.plusMinutes(10);
		}while(pointer.isAfter(LocalTime.MIDNIGHT.minusMinutes(20)));
		
		return states;
	}
}
