package com.epsm.epsdWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epsm.epsdWeb.client.ConsumerClient;
import com.epsm.epsdWeb.client.PowerStationClient;
import com.epsm.epsmCore.model.consumption.ConsumptionPermissionStub;
import com.epsm.epsmCore.model.dispatch.Command;
import com.epsm.epsmCore.model.generation.PowerStationGenerationSchedule;

@Service
public class OutgoingMessageServiceImpl implements OutgoingMessageService{

	@Autowired
	private ConsumerClient consumerClient;
	
	@Autowired
	private PowerStationClient powerStationClient;
	
	@Override
	public void sendCommand(Command command) {
		if(command == null){
			String message = "Command must not be null.";
			throw new IllegalArgumentException(message);
		}else if(command instanceof ConsumptionPermissionStub){
			consumerClient.sendConsumerPermissionToConsumer((ConsumptionPermissionStub) command);
		}else if(command instanceof PowerStationGenerationSchedule){
			powerStationClient.sendGenerationScheduleToPowerStation(
					(PowerStationGenerationSchedule) command);
		}else{
			String message = String.format("Unsuported type of Command: %s.",
					command.getClass().getSimpleName());
			throw new IllegalArgumentException(message);
		}
	}
}
