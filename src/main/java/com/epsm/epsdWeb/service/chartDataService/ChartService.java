package com.epsm.epsdWeb.service.chartDataService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epsm.epsdWeb.domain.SavedEntity;
import com.epsm.epsdWeb.domain.ValueSource;
import com.epsm.epsdWeb.repository.FrequencyDao;
import com.epsm.epsdWeb.repository.SavedGeneratorStateDao;
import com.epsm.epsdWeb.repository.TotalConsumptionDao;
import com.epsm.epsdWeb.repository.TotalGenerationDao;

@Service
public class ChartService {
	private volatile Map<String, String> chartsData = Collections.emptyMap();
	private volatile LocalDate currentChartsDataDate = LocalDate.MIN;
	private LocalDate lastAvaibleDate;
	private Logger logger = LoggerFactory.getLogger(ChartService.class);

	@Autowired
	private AvaibleDateSource dateSource;
	
	@Autowired
	private ChartDataFactory factory;
	
	public Map<String, String> getDataForCharts(){
		if(isChartsDataOutdated()){
			refreshChartsData();
		}
		
		logger.info("Invoked: getDataForCharts(), returned {}.", chartsData);
		
		return Collections.unmodifiableMap(chartsData);
	}
	
	private boolean isChartsDataOutdated(){
		getLastAvaibleDate();
		
		return currentChartsDataDate.isBefore(lastAvaibleDate);
	}
	
	private void getLastAvaibleDate(){
		lastAvaibleDate = dateSource.getLastAvaibleDate();
	}
	
	private synchronized void refreshChartsData(){
		logger.debug("Invoked: refreshChartsData()");
		
		if(! isChartsDataStillOutdated()){
			return;
		}

		Map<String, String> newChartsData = factory.getChartsData(lastAvaibleDate);
		
		if(newChartsData.size() == 0){
			return;
		}
		
		chartsData = newChartsData;
		refreshCurrentChartsDataDate(lastAvaibleDate);
	}
	
	private boolean isChartsDataStillOutdated(){
		return lastAvaibleDate.equals(LocalDate.MIN) 
				|| currentChartsDataDate.isBefore(lastAvaibleDate);
	}
	
	private void refreshCurrentChartsDataDate(LocalDate freshDate){
		currentChartsDataDate = freshDate;
	}
}
