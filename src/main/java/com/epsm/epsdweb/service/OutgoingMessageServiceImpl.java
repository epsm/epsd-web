package com.epsm.epsdweb.service;

import com.epsm.epsdweb.client.ConsumerClient;
import com.epsm.epsdweb.client.PowerStationClient;
import com.epsm.epsdcore.model.ObjectsConnector;
import com.epsm.epsmcore.model.consumption.ConsumerPermission;
import com.epsm.epsmcore.model.generation.PowerStationGenerationSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutgoingMessageServiceImpl implements ObjectsConnector {

	@Autowired
	private ConsumerClient consumerClient;
	
	@Autowired
	private PowerStationClient powerStationClient;


	@Override
	public void send(ConsumerPermission permission) {
		consumerClient.send(permission);
	}

	@Override
	public void send(PowerStationGenerationSchedule schedule) {
		powerStationClient.send(schedule);
	}
}
