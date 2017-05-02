package com.epsm.epsdweb.service.chartService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartsData {

	private LocalDate onDate;
	private Map<String, List<ValueSource>> data;
	
	public ChartsData(LocalDate onDate, Map<String, List<ValueSource>> data){
		this.onDate = onDate;
		this.data = new HashMap<>(data);
	}
	
	public List<ValueSource> getData(String chartName){
		List<ValueSource> dataToReturn = data.get(chartName);
		
		if(dataToReturn == null){
			return Collections.emptyList();
		}else{
			return dataToReturn;
		}
	}
	
	public LocalDate getDate(){
		return onDate;
	}
	
	@Override
	public String toString(){
		return String.format("Date: %s, map keyset %s", onDate, data.keySet());
	}
}
