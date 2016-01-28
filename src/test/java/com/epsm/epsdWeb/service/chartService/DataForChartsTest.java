package com.epsm.epsdWeb.service.chartService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.epsm.epsdWeb.domain.ValueSource;

public class DataForChartsTest {
	private DataForCharts dataForCharts;
	private Map<String, List<ValueSource>> data;
	private List<ValueSource> values;
	private LocalDate onDate = LocalDate.MIN;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void exceptionInconstructorIfDataIsNull(){
		expectedEx.expect(IllegalArgumentException.class);
	    expectedEx.expectMessage("DataForCharts constructor: date can't be null");
		
		new DataForCharts(null, Collections.emptyMap());
	}
	
	@Test
	public void exceptionInconstructorIfOnDateIsNull(){
		expectedEx.expect(IllegalArgumentException.class);
	    expectedEx.expectMessage("DataForCharts constructor: onData can't be null");
	    
		new DataForCharts(onDate, null);
	}
	
	@Test
	public void getDataReturnsEmptyListIfDataNotExist(){
		prepareChartDataWithoutExistData();
		
		Assert.assertEquals(Collections.emptyList(), dataForCharts.getData("SomeChart"));
	}
	
	private void prepareChartDataWithoutExistData(){
		data = new HashMap<String, List<ValueSource>>();
		dataForCharts = new DataForCharts(onDate, data);
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
		
		dataForCharts = new DataForCharts(onDate, data);
	}
}
