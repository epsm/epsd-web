package com.epsm.epsdWeb.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epsm.epsmCore.model.bothConsumptionAndGeneration.Message;
import com.epsm.epsmCore.model.consumption.ConsumerParametersStub;
import com.epsm.epsmCore.model.consumption.ConsumerState;
import com.epsm.epsmCore.model.dispatch.Dispatcher;
import com.epsm.epsmCore.model.generation.PowerStationParameters;
import com.epsm.epsmCore.model.generation.PowerStationState;

@Service
public class IncomingMessageServiceImpl implements IncomingMessageService{
	private volatile LocalDateTime powerObjectsDateTime = LocalDateTime.MIN;
	
	@Autowired
	private Dispatcher dispatcher;
	
	@Override
	public void registerConsumer(ConsumerParametersStub parameters) {
		updateObjectsDateTime(parameters);
		dispatcher.registerObject(parameters);
	}

	private void updateObjectsDateTime(Message message){
		LocalDateTime objectDateTime = message.getSimulationTimeStamp();
		LocalDateTime currentPowerObjectsDateTime = powerObjectsDateTime;
		
		if(objectDateTime.isAfter(powerObjectsDateTime)){
			powerObjectsDateTime = objectDateTime;
		}
	}
	
	@Override
	public void acceptConsumerState(ConsumerState state) {
		updateObjectsDateTime(state);
		dispatcher.acceptState(state);
	}

	@Override
	public void registerPowerStation(PowerStationParameters parameters) {
		updateObjectsDateTime(parameters);
		dispatcher.registerObject(parameters);
	}

	@Override
	public void acceptPowerStationState(PowerStationState state) {
		updateObjectsDateTime(state);
		dispatcher.acceptState(state);
	}

	@Override
	public LocalDateTime getPowerObjectsDateTime() {
		return powerObjectsDateTime;
	}
}
