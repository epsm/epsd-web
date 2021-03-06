package com.epsm.epsdWeb.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.epsm.epsmCore.model.generation.PowerStationGenerationSchedule;

@Component
public class PowerStationClient extends AbstractClient<PowerStationGenerationSchedule>{

	@Value("${api.powerstation.command}")
	private String api;
	
	public void sendGenerationScheduleToPowerStation(PowerStationGenerationSchedule schedule){
		sendMessage(schedule, api);
	}
}
