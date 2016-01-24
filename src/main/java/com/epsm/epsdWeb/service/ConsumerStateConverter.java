package com.epsm.epsdWeb.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.epsm.epsdWeb.domain.SavedConsumerState;
import com.epsm.epsmCore.model.consumption.ConsumerState;

@Component
public class ConsumerStateConverter {
	private ConsumerState consumerState;
	private long consumerId;
	private float load;
	private LocalDateTime simulationTimeStamp;
	private LocalDateTime realTimeStamp;
	private SavedConsumerState convertedState;
	
	public synchronized SavedConsumerState convert(ConsumerState consumerState) {
		saveState(consumerState);
		getData();
		cerateSavedConsumerState();
		fillOutConvertedState();
		
		return convertedState;
	}
	
	private void saveState(ConsumerState consumerState){
		this.consumerState = consumerState;
	}
	
	private void getData(){
		consumerId = consumerState.getPowerObjectId();
		load = consumerState.getLoad();
		simulationTimeStamp = consumerState.getSimulationTimeStamp();
		realTimeStamp = consumerState.getRealTimeStamp();
	}
	
	private void cerateSavedConsumerState(){
		convertedState = new SavedConsumerState();
	}
	
	private void fillOutConvertedState(){
		convertedState.setConsumerId(consumerId);
		convertedState.setLoadInMW(load);
		convertedState.setSimulationTimeStamp(simulationTimeStamp);
		convertedState.setRealTimeStamp(realTimeStamp);
	}
}
