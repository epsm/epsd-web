package com.epsm.epsdWeb.service.chartService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.epsm.epsdWeb.domain.ValueSource;

public class ValuesConverterTest {
	private ValuesConverter converter = new ValuesConverter();
	private String result;
	private List<ValueSource> values;
	
	@Test
	public void toStringMethodReturnsEmptyStringIfDataIsNull(){
		result = converter.convert(null);
		
		Assert.assertEquals("", result);
	}
	
	@Test
	public void returnsExpectedValueFirstTime(){
		String expected = "[[[10,20], 49.58],[[20,10], 50.58],[[24,00], 50]]";
		prepareFilledData();
		
		result = converter.convert(values);
		
		Assert.assertEquals(expected, result);
	}
	
	private void prepareFilledData(){
		values = new ArrayList<ValueSource>();
		ValueSource vs1 = mock(ValueSource.class);
		ValueSource vs2 = mock(ValueSource.class);
		ValueSource vs3 = mock(ValueSource.class);
		when(vs1.getPowerObjectTime()).thenReturn(Time.valueOf("20:10:00"));
		when(vs2.getPowerObjectTime()).thenReturn(Time.valueOf("10:20:00"));
		when(vs3.getPowerObjectTime()).thenReturn(Time.valueOf("23:59:59"));
		when(vs1.getValue()).thenReturn(50.578F);
		when(vs2.getValue()).thenReturn(49.578F);
		when(vs3.getValue()).thenReturn(50F);
		values.add(vs1);
		values.add(vs3);
		values.add(vs2);
	}
	
	@Test
	public void toStringMethodReturnsExpectedValueForNextTimes(){
		String expected = "[[[10,20], 49.58],[[20,10], 50.58],[[24,00], 50]]";
		prepareFilledData();
		
		result = converter.convert(values);
		result = converter.convert(values);
		
		Assert.assertEquals(expected, result);
	}
}