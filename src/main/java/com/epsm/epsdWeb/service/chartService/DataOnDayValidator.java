package com.epsm.epsdWeb.service.chartService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epsm.epsdWeb.domain.ValueSource;

@Component
public class DataOnDayValidator {
	private List<String> necessarySubcontainers;
	private Map<String, List<ValueSource>> dataContainer;
	private ValueSourceOnDayValidator sourceValidator;
	
	@Autowired
	public DataOnDayValidator(ValueSourceOnDayValidator sourceValidator, 
			List<String> necessaryDataValues) {
		
		this.necessarySubcontainers = necessaryDataValues;
		this.sourceValidator = sourceValidator;
	}
	
	public boolean isDataValid(Map<String, List<ValueSource>> dataContainer){
		saveDataContainer(dataContainer);
		
		if(dataContainerIsNull()){
			return false;
		}
		if(containerKeepsNotAllSubcontainers()){
			return false;
		}
		if(containerKeepsNullNecessarySubcontainers()){
			return false;
		}
		if(subcontainersKeepsNotAllData()){
			return false;
		}
		
		return true;
	}
	
	private void saveDataContainer(Map<String, List<ValueSource>> dataContainer){
		this.dataContainer = dataContainer;
	}
	
	private boolean dataContainerIsNull(){
		return dataContainer == null;
	}
	
	private boolean containerKeepsNotAllSubcontainers(){	
		for(String value: necessarySubcontainers){
			if(! dataContainer.containsKey(value)){
				return true;
			}
		}

		return false;
	}
	
	private boolean containerKeepsNullNecessarySubcontainers(){
		for(String containerName: necessarySubcontainers){
			List<ValueSource> subcontainer = dataContainer.get(containerName);
			
			if(subcontainer == null){
				return true;
			}
		}
		
		return false;
	}
	
	private boolean subcontainersKeepsNotAllData(){
		for(List<ValueSource> source: dataContainer.values()){
			if(! sourceValidator.isDataValid(source)){
				return true;
			}
		}
		
		return false;
	}
}
