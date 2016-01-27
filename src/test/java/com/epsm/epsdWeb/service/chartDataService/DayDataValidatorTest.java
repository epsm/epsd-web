package com.epsm.epsdWeb.service.chartDataService;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.epsm.epsdWeb.domain.SavedEntity;

public class DayDataValidatorTest {
	private DayDataValidator validator= new DayDataValidator();
	private List<SavedEntity> data = new ArrayList<SavedEntity>();
	private SavedEntity entry;
	
	@Test
	public void falseIfDataIsNull(){
		Assert.assertFalse(validator.validateDataOnDay(null));
	}
	
	@Test
	public void falseIfDataSizeUncorect(){
		data = Collections.emptyList();
		
		Assert.assertFalse(validator.validateDataOnDay(data));
	}
	
	@Test
	public void falseIfDataEntryWithNullPowerObjectTime(){
		prepareAllDataExceptEntryWithLocalTimeMaxValue();
		addEntryWithNullPowerObjectTime();
		
		Assert.assertFalse(validator.validateDataOnDay(data));
	}
	
	private void addEntryWithNullPowerObjectTime(){
		entry = new SavedEntity() {};
		data.add(entry);
	}
	
	@Test
	public void falseIfDataDoesNotHaveEntryWithLocalTimeMinValue(){
		prepareAllDataExceptEntryWithLocalTimeMaxValue();
		addEntryWithoutLocalTimeMaxValueToData();
		
		Assert.assertFalse(validator.validateDataOnDay(data));
	}
	
	private void prepareAllDataExceptEntryWithLocalTimeMaxValue(){
		LocalTime pointer = LocalTime.MIN;
		
		do{
			entry = new SavedEntity() {};
			entry.setPowerObjectTime(Time.valueOf(pointer));
			data.add(entry);
			
			pointer = pointer.plusMinutes(10);
		}while(pointer.isAfter(LocalTime.MIDNIGHT));
	}
	
	private void addEntryWithoutLocalTimeMaxValueToData(){
		entry = new SavedEntity() {};
		entry.setPowerObjectTime(Time.valueOf(LocalTime.of(1, 2, 3, 4)));
		data.add(entry);
	}
	
	@Test
	public void falseIfDataDoesNotContainAllExpectedTimeValues(){
		prepareAllDataExceptEntryWithLocalTimeMaxValue();
		addEntryWithLocalTimeMaxValueToData();
		replaceTimeInOneEntryOnInvalid();
		
		Assert.assertFalse(validator.validateDataOnDay(data));
	}
	
	private void addEntryWithLocalTimeMaxValueToData(){
		entry = new SavedEntity() {};
		entry.setPowerObjectTime(Time.valueOf(LocalTime.MAX));
		data.add(entry);
	}
	
	private void replaceTimeInOneEntryOnInvalid(){
		entry = data.get(45);
		entry.setPowerObjectTime(Time.valueOf(LocalTime.of(5, 5)));
	}
	
	@Test
	public void trueIfDataValid(){
		prepareAllDataExceptEntryWithLocalTimeMaxValue();
		addEntryWithLocalTimeMaxValueToData();
		
		Assert.assertTrue(validator.validateDataOnDay(data));
	}
}
