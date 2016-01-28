package com.epsm.epsdWeb.service.chartService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epsm.epsdWeb.domain.ValueSource;

public class DataForCharts {
	private LocalDate onDate;
	private Map<String, List<ValueSource>> data;
	
	public DataForCharts(LocalDate onDate, Map<String, List<ValueSource>> data){
		if(onDate == null){
			String message = "DataForCharts constructor: date can't be null";
			throw new IllegalArgumentException(message);
		}else if(data == null){
			String message = "DataForCharts constructor: onData can't be null";
			throw new IllegalArgumentException(message);
		}
		
		this.onDate = onDate;
		this.data = new HashMap<String, List<ValueSource>>(data);
	}
	
	public List<ValueSource> getData(String chartName){
		List<ValueSource> dataToReturn = data.get(chartName);
		
		if(dataToReturn == null){
			return Collections.emptyList();
		}else{
			return Collections.unmodifiableList(dataToReturn);
		}
	}
	
	public LocalDate getDate(){
		return onDate;
	}
}
