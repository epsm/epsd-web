package com.epsm.epsdWeb.service.chartService;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

import com.epsm.epsdWeb.domain.ValueSource;

@Component
public class ValueSourceOnDayValidator {
	private List<ValueSource> data;
	private HashSet<LocalTime> times = new HashSet<LocalTime>();
	
	public boolean isDataValid(List<ValueSource> data){
		saveData(data);
		
		if(isDataNull()){
			return false;
		}
		if(isDataSizeUncorrect()){
			return false;
		}
		if(dataContainsEntryWithNullPowerObjectTime()){
			return false;
		}
		
		getLocalTimesFromData();
		
		if(dataDoesNotHaveEntryWithLocalTimeMax()){
			return false;
		}
		if(dataDoesNotContainsEntriesWithExpectedLocalTime()){
			return false;
		}
		
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
