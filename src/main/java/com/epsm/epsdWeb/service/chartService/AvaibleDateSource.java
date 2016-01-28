package com.epsm.epsdWeb.service.chartService;

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
	private Map<String, List<ValueSource>> lastValidData;
	private Map<String, List<ValueSource>> validateData;
	private List<Date> avaibleDates;
	
	@Autowired
	private AvaibleDateDao dateDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private DataOnDayValidator validator;
	
	public LocalDate getLastAvaibleDate(){
		getDates();
		
		if(avaibleDates != null){
			sortDates();
			verifyDataOnEveryDate();
		}
		
		return lastValidDate;
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
				saveValidData();
				saveLastValidDate(date);
				break;
			}
		}
	}
	
	private boolean isDataOnDateExist(Date date){
		return lastValidDate.equals(date.toLocalDate());
	}
	
	private void getDataOnDate(Date date){
		validateData = dataSource.getData(date.toLocalDate());
	}
	
	private boolean isDataOnDateValid(){
		return validator.isDataValid(validateData);
	}
	
	private void saveValidData(){
		lastValidData = validateData;
	}
	
	private void saveLastValidDate(Date date){
		lastValidDate = date.toLocalDate();
	}
	
	public Map<String, List<ValueSource>> getData(LocalDate date){
		if(lastValidDate.equals(LocalTime.MIN)){
			return Collections.emptyMap();
		}else{
			return lastValidData;
		}
	}
}
