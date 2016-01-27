package com.epsm.epsdWeb.service.chartDataService;

import java.time.LocalTime;
import java.util.List;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import com.epsm.epsdWeb.domain.SavedEntity;

@Component
public class DayDataValidator {
	List<SavedEntity> data;
	TreeSet<LocalTime> times = new TreeSet<LocalTime>();
	
	public boolean validateDataOnDay(List<SavedEntity> data){
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
	
	private void saveData(List<SavedEntity> data){
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
		for(SavedEntity entry: data){
			if(entry.getPowerObjectTime() == null){
				return true;
			}
		}
		
		return false;
	}
	
	private void getLocalTimesFromData(){
		times.clear();
		
		for(SavedEntity entry: data){
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
