package com.epsm.epsdWeb.service.chartDataService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epsm.epsdWeb.domain.SavedGeneratorState;
import com.epsm.epsdWeb.repository.SavedGeneratorStateDao;

@Component
public class DayDateValidator {
	private LocalDate date;
	private HashSet<LocalTime> times = new HashSet<LocalTime>();
	
	@Autowired
	private SavedGeneratorStateDao dao;
	
	public boolean isDataOnDateFull(LocalDate date){
		saveDate(date);
		
		return verifyDay();
	}
	
	private void saveDate(LocalDate date){
		this.date = date;
	}
	
	private boolean verifyDay(){
		List<Long> powerStationsIds = getAllPowerStationOnDay(date);
		
		return verifyEveryPowerStation(powerStationsIds);
	}
	
	private List<Long> getAllPowerStationOnDay(LocalDate date){
		return dao.getPowerObjectsIdsOnDate(date);
	}
	
	private boolean verifyEveryPowerStation(List<Long> powerStationsIds){
		boolean containsAllData = false;
		
		for(long powerStationId: powerStationsIds){
			containsAllData = verifyEveryGenerator(powerStationId);
			
			if(containsAllData){
				return true;
			}
		}
		
		return false;
	}
	
	private boolean verifyEveryGenerator(Long powerStationId){
		boolean containsAllData = false;
		List<Integer> generatorNumbers = getGeneratorNumbers(powerStationId);
		
		for(int generatorNumber: generatorNumbers){
			containsAllData = verifyGenerator(powerStationId, generatorNumber);
			
			if(containsAllData){
				return true;
			}
		}
		
		return false;
	}
	
	private List<Integer> getGeneratorNumbers(Long powerObjectId){
		return dao.getGeneratorsNumbersOnDateForPowerStation(date, powerObjectId);
	}
	
	private boolean verifyGenerator(long powerStationId, int generatorNumber){
		List<SavedGeneratorState> states = getGeneratorStates(powerStationId, generatorNumber);
		
		return validateList(states);
	}
	
	private List<SavedGeneratorState> getGeneratorStates(long powerStationId, int generatorNumber){
		return dao.getStatesOnDateForPowerStationAndGenerator(date, powerStationId, generatorNumber);
	}
	
	private boolean validateList(List<SavedGeneratorState> states){
		clearTimeSet();
		getAllTimes(states);
		
		return verifyAllTimesPrecence();
	}
	
	private void clearTimeSet(){
		times.clear();
	}
	
	private void getAllTimes(List<SavedGeneratorState> states){
		for(SavedGeneratorState generatorState: states){
			times.add(generatorState.getPowerObjectTime().toLocalTime());
		}
	}
	
	private boolean verifyAllTimesPrecence(){
		LocalTime pointer = LocalTime.MIDNIGHT;
		
		do{
			boolean contains = times.contains(pointer);
			
			if(! contains){
				return false;
			}
			
			pointer = pointer.plusMinutes(10);
		}while(pointer.isAfter(LocalTime.MIDNIGHT));
		
		return true;
	}
}
