package com.epsm.epsdWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epsm.epsmCore.model.consumption.ConsumerParametersStub;
import com.epsm.epsmCore.model.consumption.ConsumerState;
import com.epsm.epsmCore.model.dispatch.Dispatcher;
import com.epsm.epsmCore.model.generation.PowerStationParameters;
import com.epsm.epsmCore.model.generation.PowerStationState;

@Service
public class IncomingMessageServiceImpl implements IncomingMessageService{

	@Autowired
	private Dispatcher dispatcher;
	
	@Override
	public void establishConnectionWithConsumer(ConsumerParametersStub parameters) {
		dispatcher.establishConnection(parameters);
	}

	@Override
	public void acceptConsumerState(ConsumerState state) {
		dispatcher.acceptState(state);
	}

	@Override
	public void establishConnectionWithPowerStation(PowerStationParameters parameters) {
		dispatcher.establishConnection(parameters);
	}

	@Override
	public void acceptPowerStationState(PowerStationState state) {
		dispatcher.acceptState(state);
	}
}
