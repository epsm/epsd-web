package com.epsm.epsdWeb.service.chartService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.epsm.epsdWeb.domain.ValueSource;

@RunWith(MockitoJUnitRunner.class)
public class ChartServiceTest {
	private Map<String, String> charts;
	private ValueSource frequency = mock(ValueSource.class);
	private ValueSource generation = mock(ValueSource.class);
	private ValueSource consumption = mock(ValueSource.class);
	
	@InjectMocks
	private ChartService service;
	
	@Mock
	private ChartsDataSource dataSource;

	@Spy
	private ValuesConverter converter;
	
	@Test
	public void returnMapWithOnlyOneParameterDateEqualsToNoData(){
		charts = service.getDataForCharts();
		
		Assert.assertEquals(1, charts.size());
		Assert.assertEquals("no data", charts.get("date"));
	}
	
	@Test
	public void triesToGetData(){
		service.getDataForCharts();
		
		verify(dataSource).getData();
	}
	
	@Test
	public void doesNotCreateNewChartsIfTheyWereCreatedPreviouslyAndThereIsNoNewData(){
		makeDataSourceGiveData();
		Map<String, String> charts_old = service.getDataForCharts();
		
		Map<String, String> charts_new = service.getDataForCharts();
		
		Assert.assertTrue(charts_old == charts_new);
	}
	
	private void makeDataSourceGiveData(){
		ChartsData data = mock(ChartsData.class);
		when(frequency.getPowerObjectTime()).thenReturn(Time.valueOf("10:10:00"));
		when(generation.getPowerObjectTime()).thenReturn(Time.valueOf("11:11:00"));
		when(consumption.getPowerObjectTime()).thenReturn(Time.valueOf("12:12:00"));
		when(frequency.getValue()).thenReturn(11.11f);
		when(generation.getValue()).thenReturn(22.22f);
		when(consumption.getValue()).thenReturn(33.33f);
		when(data.getData("frequency")).thenReturn(Arrays.asList(frequency));
		when(data.getData("generation")).thenReturn(Arrays.asList(generation));
		when(data.getData("consumption")).thenReturn(Arrays.asList(consumption));
		when(data.getDate()).thenReturn(LocalDate.MAX);
		when(dataSource.getData()).thenReturn(data);
	}
	
	@Test
	public void makesCorrectChartsIfDataObtained(){
		makeDataSourceGiveData();
		
		charts = service.getDataForCharts();
		
		Assert.assertEquals(4, charts.size());
		Assert.assertEquals("[[[10,10], 11.11]]", charts.get("frequencyChartData"));
		Assert.assertEquals("[[[11,11], 22.22]]", charts.get("generationChartData"));
		Assert.assertEquals("[[[12,12], 33.33]]", charts.get("consumptionChartData"));
		Assert.assertEquals("+999999999-12-31", charts.get("date"));
	}
}
