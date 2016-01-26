package com.epsm.epsdWeb.service.chartDataService;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epsm.epsdWeb.repository.SavedConsumerStateDao;
import com.epsm.epsdWeb.repository.SavedGeneratorStateDao;

@Service
public class ChartService {
	private volatile Map<String, String> chartsData;
	private volatile LocalDate currentChartsDataDate = LocalDate.MIN;
	
	@Autowired
	private SavedGeneratorStateDao generatorDao;
	
	@Autowired
	private ChartsDataSource dataSource;
	
	public Map<String, String> getDataForCharts(){
		if(isChartsDataOutdated()){
			refreshChartsData();
		}
		
		return chartsData;
	}
	
	private boolean isChartsDataOutdated(){
		return currentChartsDataDate.isBefore(getLastFullDayFromDB());
	}
	
	private LocalDate getLastFullDayFromDB(){
		LocalDate lastEntryInDatabase = generatorDao.getLastEntryDate();
		LocalDate preLastDateInDatabase = lastEntryInDatabase.minusDays(1);
		
		return preLastDateInDatabase;
	}
	
	private synchronized void refreshChartsData(){
		if(! isChartsDataOutdated()){
			return;
		}
		
		LocalDate freshDate = getLastFullDayFromDB();
		chartsData = dataSource.getDataForChartsOnDate(freshDate);
	}
}
