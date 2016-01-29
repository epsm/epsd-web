package com.epsm.epsdWeb.service.chartService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epsm.epsdWeb.domain.ValueSource;
import com.epsm.epsdWeb.repository.AvaibleDateDao;
import com.epsm.epsdWeb.repository.FrequencyDao;
import com.epsm.epsdWeb.repository.TotalConsumptionDao;
import com.epsm.epsdWeb.repository.TotalGenerationDao;

@Component
public class ChartsDataSource {
	private ChartsData chartsData;
	private LocalDate lastValidDate;
	private Map<String, List<ValueSource>> dataContainer;
	private List<Date> avaibleDates;
	private Logger logger;
	
	@Autowired
	private AvaibleDateDao dateDao;
	
	@Autowired
	private FrequencyDao frequencyDao;
	
	@Autowired
	private TotalGenerationDao generationDao;
	
	@Autowired
	private TotalConsumptionDao consumptionDao;
	
	@Autowired
	private DayChartsDataValidator validator;
	
	public ChartsDataSource(){
		chartsData = new ChartsData(LocalDate.MIN, Collections.emptyMap());
		lastValidDate = LocalDate.MIN;
		dataContainer = new HashMap<String, List<ValueSource>>();
		logger = LoggerFactory.getLogger(ChartsDataSource.class);
	}
	
	public synchronized ChartsData getData(){
		getDates();
		
		if(avaibleDates != null){
			sortDates();
			verifyDataOnEveryDate();
		}
		
		logger.debug("Requested: ChartsData, returned {}.", chartsData);
		return chartsData;
	}
	
	private void getDates(){
		avaibleDates = dateDao.getDates();
	}
	
	private void sortDates(){
		Collections.reverse(avaibleDates);
	}
	
	private void verifyDataOnEveryDate(){
		for(Date date: avaibleDates){
			if(isDataOnDateExist(date)){
				break;
			}
			
			getDataOnDate(date);
			
			if(isDataOnDateValid(date)){
				saveLastValidDate(date);
				createNewDataForCharts();
				break;
			}
		}
	}
	
	private boolean isDataOnDateExist(Date date){
		boolean exist = lastValidDate.equals(date.toLocalDate());
		logger.debug("Invoked: isDataOnDateExist({}) {}.", date, exist);
		
		return exist;
	}
	
	private void getDataOnDate(Date date){
		clearDataContainer();
		getNewData(date.toLocalDate());
	}
	
	private void clearDataContainer(){
		dataContainer.clear();
	}
	
	private void getNewData(LocalDate date){
		List<ValueSource> frequencyData = frequencyDao.getFrequencies(date);
		List<ValueSource> generationData = generationDao.getTotalGenerations(date);
		List<ValueSource> consumptionData = consumptionDao.getTotalConsumptions(date);
		
		dataContainer.put("frequency", frequencyData);
		dataContainer.put("generation", generationData);
		dataContainer.put("consumption", consumptionData);
	}
	
	private boolean isDataOnDateValid(Date date){
		boolean valid = validator.isDataValid(dataContainer);
		logger.debug("Invoked: isDataOnDateValid({}) {}.", date, valid);
		
		return valid;
	}
	
	private void createNewDataForCharts(){
		chartsData = new ChartsData(lastValidDate, dataContainer);
	}
	
	private void saveLastValidDate(Date date){
		lastValidDate = date.toLocalDate();
	}
}
