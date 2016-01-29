package com.epsm.epsdWeb.service.chartService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epsm.epsdWeb.domain.ValueSource;

@Component
public class DayChartsDataValidator {
	private List<String> necessarySubcontainers;
	private Map<String, List<ValueSource>> dataContainer;
	private DayChartDataValidator sourceValidator;
	private Logger logger = LoggerFactory.getLogger(DayChartsDataValidator.class);
	
	@Autowired
	public DayChartsDataValidator(DayChartDataValidator sourceValidator) {
		this.sourceValidator = sourceValidator;
		necessarySubcontainers = Arrays.asList("frequency", "generation", "consumption");
		logger.debug("Created: validator with necessarySubcontainers = {}." + necessarySubcontainers);
	}
	
	public boolean isDataValid(Map<String, List<ValueSource>> dataContainer){
		saveDataContainer(dataContainer);
		
		if(dataContainerIsNull()){
			logger.debug("Validated: data container is null.");
			return false;
		}
		if(containerKeepsNotAllSubcontainers()){
			logger.debug("Validated: container keeps not all subcontainers.");
			return false;
		}
		if(containerKeepsNullNecessarySubcontainers()){
			logger.debug("Validated: container keeps null necessarySubcontainer(s).");
			return false;
		}
		if(subcontainersKeepsNotAllData()){
			logger.debug("Validated: subcontainer(s) keeps not all data().");
			return false;
		}
		
		logger.debug("Validated: data valid.");
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
