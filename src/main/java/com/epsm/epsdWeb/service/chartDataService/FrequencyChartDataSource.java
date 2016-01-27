package com.epsm.epsdWeb.service.chartDataService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epsm.epsdWeb.domain.SavedGeneratorState;
import com.epsm.epsdWeb.repository.SavedGeneratorStateDao;

@Component
public class FrequencyChartDataSource {
	private LocalDate date;
	private HashSet<LocalTime> times = new HashSet<LocalTime>();
	private Map<LocalTime, Float> chartData;
	private boolean isDataFull;
	
	@Autowired
	private SavedGeneratorStateDao dao;
	
	public ChartData getChartData(LocalDate date){
		saveDate(date);
		isDataFull = verifyDay();
		
		return prepareFullOrEmptyChartData();
	}
	
	private void saveDate(LocalDate date){
		this.date = date;
	}
	
	private boolean verifyDay(){
		List<Long> powerStationsIds = getAllPowerStationOnDay(date);
		
		return verifyEveryPowerStation(powerStationsIds);
	}
	
	private List<Long> getAllPowerStationOnDay(LocalDate date){
		return dao.getPowerObjectsIds(date);
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
		return dao.getGeneratorsNumbers(date, powerObjectId);
	}
	
	private boolean verifyGenerator(long powerStationId, int generatorNumber){
		List<SavedGeneratorState> states = getGeneratorStates(powerStationId, generatorNumber);
		boolean dataForDay = validateList(states);
		boolean dataOnMidnightNextDay = isDataOnMidnightNextDayPresent(powerStationId, generatorNumber);
		
		if(dataForDay && dataOnMidnightNextDay){
			fillChartData(states, powerStationId, generatorNumber);
		}
		
		return dataForDay && dataOnMidnightNextDay;
	}
	
	private List<SavedGeneratorState> getGeneratorStates(long powerStationId, int generatorNumber){
		return dao.getStates(date, powerStationId, generatorNumber);
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
	
	private boolean isDataOnMidnightNextDayPresent(long powerObjectId, int generatorNumber){
		LocalDateTime necessaryDateTime = LocalDateTime.of(date, LocalTime.MIDNIGHT);
		Float generation = dao.getFrequency(necessaryDateTime, powerObjectId, generatorNumber);
		
		return generation != Float.NEGATIVE_INFINITY;
	}
	
	private void fillChartData(List<SavedGeneratorState> states, long powerObjectId, int generatorNumber){
		LocalDateTime necessaryDateTime = LocalDateTime.of(date, LocalTime.MIDNIGHT);
		chartData = new HashMap<LocalTime, Float>();
		
		for(SavedGeneratorState generatorState: states){
			LocalTime time = generatorState.getPowerObjectTime().toLocalTime();
			Float frequency = generatorState.getFrequency();
			chartData.put(time, frequency);
		}
		
		Float nextMidnightFrequency = dao.getFrequency(necessaryDateTime, powerObjectId, generatorNumber);
		chartData.put(LocalTime.MAX, nextMidnightFrequency);
	}

	private ChartData prepareFullOrEmptyChartData(){
		if(isDataFull){
			return new ChartData(chartData);
		}else{
			return new ChartData(null);
		}
	}
}
