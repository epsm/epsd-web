package com.epsm.epsdWeb.service.chartDataService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.epsm.epsdWeb.domain.ValueSource;

public class ValueSourceOnDayValidatorTest {
	private ValueSourceOnDayValidator validator= new ValueSourceOnDayValidator();
	private List<ValueSource> data = new ArrayList<ValueSource>();
	private ValueSource entry;
	
	@Test
	public void falseIfDataIsNull(){
		Assert.assertFalse(validator.isDataValid(null));
	}
	
	@Test
	public void falseIfDataSizeUncorect(){
		data = Collections.emptyList();
		
		Assert.assertFalse(validator.isDataValid(data));
	}
	
	@Test
	public void falseIfDataEntryWithNullPowerObjectTime(){
		prepareAllDataExceptEntryWithLocalTimeMaxValue();
		addEntryWithNullPowerObjectTime();
		
		Assert.assertFalse(validator.isDataValid(data));
	}
	
	private void addEntryWithNullPowerObjectTime(){
		entry = mock(ValueSource.class);
		data.add(entry);
	}
	
	@Test
	public void falseIfDataDoesNotHaveEntryWithLocalTimeMinValue(){
		prepareAllDataExceptEntryWithLocalTimeMaxValue();
		addEntryWithoutLocalTimeMaxValueToData();
		
		Assert.assertFalse(validator.isDataValid(data));
	}
	
	private void prepareAllDataExceptEntryWithLocalTimeMaxValue(){
		LocalTime pointer = LocalTime.MIN;
		
		do{
			entry = mock(ValueSource.class);
			when(entry.getPowerObjectTime()).thenReturn(Time.valueOf(pointer));
			data.add(entry);
			
			pointer = pointer.plusMinutes(10);
		}while(pointer.isAfter(LocalTime.MIDNIGHT));
	}
	
	private void addEntryWithoutLocalTimeMaxValueToData(){
		entry = mock(ValueSource.class);
		when(entry.getPowerObjectTime()).thenReturn(Time.valueOf(LocalTime.of(1, 2, 3, 4)));
		data.add(entry);
	}
	
	@Test
	public void falseIfDataDoesNotContainAllExpectedTimeValues(){
		prepareAllDataExceptEntryWithLocalTimeMaxValue();
		addEntryWithLocalTimeMaxValueToData();
		replaceTimeInOneEntryOnInvalid();
		
		Assert.assertFalse(validator.isDataValid(data));
	}
	
	private void addEntryWithLocalTimeMaxValueToData(){
		entry = mock(ValueSource.class);
		when(entry.getPowerObjectTime()).thenReturn(Time.valueOf(LocalTime.MAX));
		data.add(entry);
	}
	
	private void replaceTimeInOneEntryOnInvalid(){
		entry = data.get(45);
		when(entry.getPowerObjectTime()).thenReturn(Time.valueOf(LocalTime.of(5, 5)));
	}
	
	@Test
	public void trueIfDataValid(){
		prepareAllDataExceptEntryWithLocalTimeMaxValue();
		addEntryWithLocalTimeMaxValueToData();
		
		Assert.assertTrue(validator.isDataValid(data));
	}
}
