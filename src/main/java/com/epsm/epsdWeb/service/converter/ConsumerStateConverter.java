package com.epsm.epsdWeb.service.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.epsm.epsdWeb.domain.SavedConsumerState;
import com.epsm.epsmCore.model.consumption.ConsumerState;

@Component("consStateConverter")//name for rhcloud WildFly, on local Tomcat no problem
public class ConsumerStateConverter extends SavedPowerObjectConverter<ConsumerState>{
	private float load;
	private SavedConsumerState convertedState;
	private Logger logger = LoggerFactory.getLogger(ConsumerStateConverter.class);
	
	public synchronized SavedConsumerState convert(ConsumerState consumerState) {
		saveSource(consumerState);
		getGeneralFields();
		getLoadField();
		createSavedConsumerState();
		fillGeneralFields(convertedState);
		fillOutLoadField();
		logger.debug("Converted: ConsumerState to SavedConsumerState.");
		
		return convertedState;
	}
	
	private void getLoadField(){
		load = source.getLoadInMW();
	}
	
	private void createSavedConsumerState(){
		convertedState = new SavedConsumerState();
	}
	
	private void fillOutLoadField(){
		convertedState.setLoadInMW(load);
	}
}
