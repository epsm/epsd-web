package com.epsm.epsdWeb.service.chartService;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class ChartDataTest {
	private ChartData chartData;
	
	@Test
	public void sizeMethodReturnsZeroByIfThereIsNoMap(){
		prepareNullMapChartData();
		
		Assert.assertEquals(0, chartData.size());
	}
	
	private void prepareNullMapChartData(){
		chartData = new ChartData(null);
	}
	
	@Test
	public void sizeMethodReturnsMapSize(){
		prepareFilledMap();
		
		Assert.assertEquals(3, chartData.size());
	}
	
	private void prepareFilledMap(){
		Map<LocalTime, Float> values = new HashMap<LocalTime, Float>();
		values.put(LocalTime.of(10, 20, 30, 44), 49.578F);
		values.put(LocalTime.of(20, 10, 31, 47), 50.578F);
		values.put(LocalTime.MAX, 50F);
		chartData = new ChartData(values);
	}
	
	@Test
	public void toStringMethodReturnsEmptyStringIfMapIsNull(){
		prepareNullMapChartData();
		
		String result = chartData.toString();
		
		Assert.assertEquals("", result);
	}
	
	@Test
	public void toStringMethodReturnsExpectedValueFirstTime(){
		String expected = "[[[10,20], 49.58],[[20,10], 50.58],[[24,00], 50]]";
		prepareFilledMap();
		
		String result = chartData.toString();
		
		System.out.println(result);
		
		Assert.assertEquals(expected, result);
	}
	
	@Test
	public void toStringMethodReturnsExpectedValueForNextTimes(){
		String expected = "[[[10,20], 49.58],[[20,10], 50.58],[[24,00], 50]]";
		prepareFilledMap();
		chartData.toString();
		
		String result = chartData.toString();
		
		Assert.assertEquals(expected, result);
	}
}