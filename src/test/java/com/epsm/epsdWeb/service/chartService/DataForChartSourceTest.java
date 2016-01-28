package com.epsm.epsdWeb.service.chartService;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epsm.epsdWeb.domain.ValueSource;
import com.epsm.epsdWeb.repository.AvaibleDateDao;
import com.epsm.epsdWeb.repository.FrequencyDao;
import com.epsm.epsdWeb.repository.TotalConsumptionDao;
import com.epsm.epsdWeb.repository.TotalGenerationDao;

@RunWith(MockitoJUnitRunner.class)
public class DataForChartSourceTest {
	
	@InjectMocks
	private DataForChartSource dataSource;
	
	@Mock
	private AvaibleDateDao dateDao;
	
	@Mock
	private FrequencyDao frequencyDao;
	
	@Mock
	private TotalGenerationDao generationDao;
	
	@Mock
	private TotalConsumptionDao consumptionDao;
	
	@Mock
	private DataOnDayValidator validator;
	
	private ValueSource frequency = mock(ValueSource.class);
	private ValueSource generation = mock(ValueSource.class);
	private ValueSource consumption = mock(ValueSource.class);
	
	@Test
	public void returnsExistDataForChartsIfRetrievedNullInsteadDates(){
		when(dateDao.getDates()).thenReturn(null);
		
		Assert.assertEquals(LocalDate.MIN, dataSource.getData().getDate());
	}
	
	@Test
	public void returnsExistDataForChartsIfRetrievedEmptyListOfDates(){
		when(dateDao.getDates()).thenReturn(Collections.emptyList());
		
		Assert.assertEquals(LocalDate.MIN, dataSource.getData().getDate());
	}
	
	@Test
	public void triesToRetrieveDatesFromAvaibleDateDao(){
		dataSource.getData();
		
		verify(dateDao).getDates();
	}
	
	@Test
	public void doesNotTryRetrievePowerSystemParametersIfCantRetrieveDate(){
		dataSource.getData();
		
		verify(frequencyDao, never()).getFrequencies(any());
		verify(generationDao, never()).getTotalGenerations(any());
		verify(consumptionDao, never()).getTotalConsumptions(any());
	}
	
	@Test
	public void triesRetrievePowerSystemParametersIfRetrievedFreshDate(){
		prepareFreshDate();
		
		dataSource.getData();
		
		verify(frequencyDao).getFrequencies(any());
		verify(generationDao).getTotalGenerations(any());
		verify(consumptionDao).getTotalConsumptions(any());
	}
	
	private void prepareFreshDate(){
		when(dateDao.getDates()).thenReturn(Arrays.asList(Date.valueOf(LocalDate.MAX)));
	}
	
	@Test
	public void doesNotTryRetrievePowerSystemParametersIfRetrieveExistValidatedDate(){
		prepareFreshDate();
		prepareDataSet();
		makeValidatorAnswerTrue();
		
		dataSource.getData();
		dataSource.getData();
		
		verify(frequencyDao).getFrequencies(any());
		verify(generationDao).getTotalGenerations(any());
		verify(consumptionDao).getTotalConsumptions(any());
	}

	private void prepareDataSet(){
		when(frequencyDao.getFrequencies(isA(LocalDate.class))).thenReturn(
				Arrays.asList(frequency));
		when(generationDao.getTotalGenerations(isA(LocalDate.class))).thenReturn(
				Arrays.asList(generation));
		when(consumptionDao.getTotalConsumptions(isA(LocalDate.class))).thenReturn(
				Arrays.asList(consumption));
	}
	
	private void makeValidatorAnswerTrue(){
		when(validator.isDataValid(any())).thenReturn(true);
	}
	
	@Test
	public void returnsNewCorrectDataForChartsIfRightDataRetrieved(){
		prepareFreshDate();
		prepareDataSet();
		makeValidatorAnswerTrue();
		
		DataForCharts data = dataSource.getData();
		
		Assert.assertEquals(getLocalDateAsMaxValueOfSQLDate(), data.getDate());
		Assert.assertTrue(data.getData("frequency").contains(frequency));
		Assert.assertTrue(data.getData("generation").contains(generation));
		Assert.assertTrue(data.getData("consumption").contains(consumption));
	}
	
	private LocalDate getLocalDateAsMaxValueOfSQLDate(){
		return Date.valueOf(LocalDate.MAX).toLocalDate();
	}
	
	@Test
	public void returnsOldDataForChartsIfWrongDataRetrieved(){
		DataForCharts existData = dataSource.getData();
		prepareFreshDate();
		prepareDataSet();
		makeValidatorAnswerFalse();
		
		DataForCharts newData = dataSource.getData();
		
		Assert.assertTrue(existData == newData);
	}
	
	private void makeValidatorAnswerFalse(){
		when(validator.isDataValid(any())).thenReturn(false);
	}
}
