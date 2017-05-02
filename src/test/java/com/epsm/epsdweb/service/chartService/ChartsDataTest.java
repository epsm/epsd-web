package com.epsm.epsdweb.service.chartService;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

public class ChartsDataTest {

	private ChartsData dataForCharts;
	private Map<String, List<ValueSource>> data;
	private List<ValueSource> values;
	private LocalDate onDate = LocalDate.MIN;
	
	@Test
	public void getDataReturnsEmptyListIfDataNotExist(){
		prepareChartDataWithoutExistData();
		
		Assert.assertEquals(Collections.emptyList(), dataForCharts.getData("SomeChart"));
	}
	
	private void prepareChartDataWithoutExistData(){
		data = new HashMap<String, List<ValueSource>>();
		dataForCharts = new ChartsData(onDate, data);
	}
	
	@Test
	public void getDataReturnsExpectdData(){
		prepareChartDataWithExistData();
		
		Assert.assertEquals(values, dataForCharts.getData("SomeChart"));
	}
	
	private void prepareChartDataWithExistData(){
		data = new HashMap<String, List<ValueSource>>();
		values = new ArrayList<ValueSource>();
		data.put("SomeChart", values);
		
		dataForCharts = new ChartsData(onDate, data);
	}
}
