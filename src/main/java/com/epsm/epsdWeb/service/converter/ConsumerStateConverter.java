package com.epsm.epsdWeb.service.converter;

import org.springframework.stereotype.Component;

import com.epsm.epsdWeb.domain.SavedConsumerState;
import com.epsm.epsmCore.model.consumption.ConsumerState;

@Component("consStateConverter")//name for WildFly, on Tomcat no problem
public class ConsumerStateConverter extends SavedPowerObjectConverter<ConsumerState>{
	private float load;
	private SavedConsumerState convertedState;
	
	public synchronized SavedConsumerState convert(ConsumerState consumerState) {
		saveSource(consumerState);
		getGeneralFields();
		getLoadField();
		createSavedConsumerState();
		fillGeneralFields(convertedState);
		fillOutLoadField();
		
		return convertedState;
	}
	
	private void getLoadField(){
		load = source.getLoad();
	}
	
	private void createSavedConsumerState(){
		convertedState = new SavedConsumerState();
	}
	
	private void fillOutLoadField(){
		convertedState.setLoadInMW(load);
	}
}
