package com.epsm.epsdWeb.service.chartDataService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChartsDataSource {
	private LocalDate date;
	private Map<String, String> chartsData =  new HashMap<String, String>();
	
	@Autowired
	private DayDateValidator validator;
	
	public Map<String, String> getDataForChartsOnDate(LocalDate date){
		saveDate(date);
		clearMap();
		
		if(isThereDataOnDate()){
			fillChartsData();
		}
		
		return chartsData;
	}
	
	private void saveDate(LocalDate date){
		this.date = date;
	}
	
	private void clearMap(){
		chartsData.clear();
	}
	
	private boolean isThereDataOnDate(){
		return validator.isDataOnDateFull(date);
	}
	
	private void fillChartsData(){
		
	}
	
	private void fillFrequencyChartData(){
		
	}
}
