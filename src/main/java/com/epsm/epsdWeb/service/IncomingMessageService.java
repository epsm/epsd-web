package com.epsm.epsdWeb.service;

import com.epsm.epsmCore.model.consumption.ConsumerParametersStub;
import com.epsm.epsmCore.model.consumption.ConsumerState;
import com.epsm.epsmCore.model.generation.PowerStationParameters;
import com.epsm.epsmCore.model.generation.PowerStationState;

public interface IncomingMessageService{
	boolean registerConsumer(ConsumerParametersStub parameters);
	void acceptConsumerState(ConsumerState state);
	boolean registerPowerStation(PowerStationParameters parameters);
	void acceptPowerStationState(PowerStationState state);
}
