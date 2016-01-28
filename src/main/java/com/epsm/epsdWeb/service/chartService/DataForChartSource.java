package com.epsm.epsdWeb.service.chartService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epsm.epsdWeb.domain.ValueSource;
import com.epsm.epsdWeb.repository.AvaibleDateDao;
import com.epsm.epsdWeb.repository.FrequencyDao;
import com.epsm.epsdWeb.repository.TotalConsumptionDao;
import com.epsm.epsdWeb.repository.TotalGenerationDao;

@Component
public class DataForChartSource {
	private DataForCharts dataForCharts;
	private LocalDate lastValidDate;
	private Map<String, List<ValueSource>> dataContainer;
	private List<Date> avaibleDates;
	
	@Autowired
	private AvaibleDateDao dateDao;
	
	@Autowired
	private FrequencyDao frequencyDao;
	
	@Autowired
	private TotalGenerationDao generationDao;
	
	@Autowired
	private TotalConsumptionDao consumptionDao;
	
	@Autowired
	private DataOnDayValidator validator;
	
	public DataForChartSource(){
		dataForCharts = new DataForCharts(LocalDate.MIN, Collections.emptyMap());
		lastValidDate = LocalDate.MIN;
		dataContainer = new HashMap<String, List<ValueSource>>();
	}
	
	public synchronized DataForCharts getData(){
		getDates();
		
		if(avaibleDates != null){
			sortDates();
			verifyDataOnEveryDate();
		}
		
		return dataForCharts;
	}
	
	private void getDates(){
		avaibleDates = dateDao.getDates();
	}
	
	private void sortDates(){
		Collections.sort(avaibleDates);
	}
	
	private void verifyDataOnEveryDate(){
		for(Date date: avaibleDates){
			if(isDataOnDateExist(date)){
				break;
			}
			
			getDataOnDate(date);
			
			if(isDataOnDateValid()){
				saveLastValidDate(date);
				createNewDataForCharts();
				break;
			}
		}
	}
	
	private boolean isDataOnDateExist(Date date){
		return lastValidDate.equals(date.toLocalDate());
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
	
	private boolean isDataOnDateValid(){
		return validator.isDataValid(dataContainer);
	}
	
	private void createNewDataForCharts(){
		dataForCharts = new DataForCharts(lastValidDate, dataContainer);
	}
	
	private void saveLastValidDate(Date date){
		lastValidDate = date.toLocalDate();
	}
}
