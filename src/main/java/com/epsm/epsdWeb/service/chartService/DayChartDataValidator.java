package com.epsm.epsdWeb.service.chartService;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.epsm.epsdWeb.domain.ValueSource;

@Component
public class DayChartDataValidator {
	private List<ValueSource> data;
	private HashSet<LocalTime> times = new HashSet<LocalTime>();
	private Logger logger = LoggerFactory.getLogger(DayChartDataValidator.class);

	
	public boolean isDataValid(List<ValueSource> data){
		saveData(data);
		
		if(isDataNull()){
			logger.debug("Validated: data is null;");
			return false;
		}
		if(isDataSizeUncorrect()){
			logger.debug("Validated: data is incorrect ({});", data.size());
			return false;
		}
		if(dataContainsEntryWithNullPowerObjectTime()){
			logger.debug("Validated: data contains entry with null powerObjectTime;");
			return false;
		}
		
		getLocalTimesFromData();
		
		if(dataDoesNotHaveEntryWithLocalTimeMax()){
			logger.debug("Validated: data does not have entry with LocalTime.MAX;");
			return false;
		}
		if(dataDoesNotContainsEntriesWithExpectedLocalTime()){
			logger.debug("Validated: data does not contains entries with expected LocalTime;");
			return false;
		}
		
		logger.debug("Validated: data valid.");
		return true;
	}
	
	private void saveData(List<ValueSource> data){
		this.data = data;
	}
	
	private boolean isDataNull(){
		return data == null;
	}
	
	private boolean isDataSizeUncorrect(){
		int hoursInDay = 24;
		int entriesInHour = 6;
		int nextMidnightEntry = 1;
		int expectedSize = hoursInDay * entriesInHour + nextMidnightEntry;
				
		return data.size() != expectedSize;
	}
	
	private boolean dataContainsEntryWithNullPowerObjectTime(){
		for(ValueSource entry: data){
			if(entry.getPowerObjectTime() == null){
				return true;
			}
		}
		
		return false;
	}
	
	private void getLocalTimesFromData(){
		times.clear();
		
		for(ValueSource entry: data){
			times.add(entry.getPowerObjectTime().toLocalTime());
		}
	}
	
	private boolean dataDoesNotHaveEntryWithLocalTimeMax(){
		return !times.contains(LocalTime.of(23, 59, 59));
	}
	
	private boolean dataDoesNotContainsEntriesWithExpectedLocalTime(){
		LocalTime pointer = LocalTime.MIDNIGHT;
		
		do{
			if(! times.contains(pointer)){
				return true;
			}
			
			pointer = pointer.plusMinutes(10);
		}while(pointer.isAfter(LocalTime.MIDNIGHT));
		
		return false;
	}
}
