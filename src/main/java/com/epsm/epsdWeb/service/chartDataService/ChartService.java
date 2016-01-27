package com.epsm.epsdWeb.service.chartDataService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epsm.epsdWeb.repository.SavedGeneratorStateDao;

@Service
public class ChartService {
	private volatile Map<String, String> chartsData;
	private volatile LocalDate currentChartsDataDate;
	private Logger logger;
	
	@Autowired
	private SavedGeneratorStateDao generatorDao;
	
	@Autowired
	private FrequencyChartDataSource frequencyDataSource;
	
	public ChartService(){
		chartsData = new HashMap<String, String>();
		currentChartsDataDate = LocalDate.MIN;
		logger = LoggerFactory.getLogger(ChartService.class);
		
		chartsData.put("date", "still no information");
	}
	
	public Map<String, String> getDataForCharts(){
		if(isChartsDataOutdated()){
			refreshChartsData();
		}
		
		logger.info("Invoked: getDataForCharts(), returned {}.", chartsData);
		
		return Collections.unmodifiableMap(chartsData);
	}
	
	private boolean isChartsDataOutdated(){
		return currentChartsDataDate.isBefore(getLastFullDayFromDB());
	}
	
	private LocalDate getLastFullDayFromDB(){
		LocalDate lastEntryInDatabase = generatorDao.getLastEntryDate();
		
		if(lastEntryInDatabase == null || lastEntryInDatabase.equals(LocalDate.MIN)){
			return LocalDate.MIN;
		}else{
			return lastEntryInDatabase.minusDays(1);
		}
	}
	
	private synchronized void refreshChartsData(){
		logger.debug("Invoked: refreshChartsData()");
		
		if(! isChartsDataOutdated()){
			logger.debug("Invoked: chartData isn't out of date in refreshChartsData()");
			return;
		}
		
		logger.debug("Invoked: chartData is out of date in refreshChartsData()");
		
		LocalDate freshDate = getLastFullDayFromDB();
		chartsData = createNewChartData(freshDate);
		refreshCurrentChartsDataDate(freshDate);
	}
	
	private Map<String, String> createNewChartData(LocalDate freshDate){
		Map<String, String> chartsData = new HashMap<String, String>();
		
		addDate(freshDate, chartsData);
		addFrequencyChartData(freshDate, chartsData);
		
		return chartsData;
	}
	
	private void addDate(LocalDate date, Map<String, String> chartsData){
		chartsData.put("date", date.toString());
	}
	
	private void addFrequencyChartData(LocalDate freshDate, Map<String, String> chartsData){
		ChartData frequencyChartData = frequencyDataSource.getChartData(freshDate);
		chartsData.put("frequencyChartData", frequencyChartData.toString());
		
		logger.debug("Invoked: addFrequencyChartData(...), added {}", frequencyChartData);
	}
	
	private void refreshCurrentChartsDataDate(LocalDate freshDate){
		currentChartsDataDate = freshDate;
	}
}
