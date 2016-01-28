package com.epsm.epsdWeb.service.chartDataService;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epsm.epsdWeb.domain.ValueSource;
import com.epsm.epsdWeb.repository.AvaibleDateDao;

@Component
public class AvaibleDateSource {
	private LocalDate lastValidDate = LocalDate.MIN;
	private LocalDate valiadateDate;
	private Map<String, ValueSource> lastValidData;
	private Map<String, ValueSource> validateData;
	private List<String> necessaryDataValues;
	private List<Date> avaibleDates;
	
	@Autowired
	public AvaibleDateSource(List<String> necessaryDataValues) {
		this.necessaryDataValues = necessaryDataValues;
	}
	
	@Autowired
	private AvaibleDateDao dateDao;
	
	@Autowired
	private ChartsDataSource dataSource;
	
	public LocalDate getLastAvaibleDate(){
		getDates();
		sortDates();
		
		return lastValidDate;
	}
	
	private void getDates(){
		avaibleDates = dateDao.getDates();
	}
	
	private void sortDates(){
		Collections.sort(avaibleDates);
	}
	
	private void validateDatesData(){
		for(Date date: avaibleDates){
			if(date.to)
			if(isDataOnDateValid)
		}
	}
	
	private isDataOnDatePreviouslyValidated(){
		
	}
	
	private boolean isDataOnDateValid(){
		
	}
	
	public Map<String, ValueSource> getValidData(LocalDate date){
		if(lastValidDate.equals(LocalTime.MIN)){
			return Collections.emptyMap();
		}else{
			return lastValidData;
		}
	}
}
