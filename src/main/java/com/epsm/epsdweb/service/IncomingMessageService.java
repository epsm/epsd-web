package com.epsm.epsdweb.service;

import com.epsm.epsdcore.model.Dispatcher;
import com.epsm.epsmcore.model.consumption.RandomLoadConsumerParameters;
import com.epsm.epsmcore.model.consumption.ScheduledLoadConsumerParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epsm.epsmcore.model.consumption.ConsumerState;
import com.epsm.epsmcore.model.generation.PowerStationParameters;
import com.epsm.epsmcore.model.generation.PowerStationState;

import java.util.List;

@Service
public class IncomingMessageService {

	@Autowired
	private Dispatcher dispatcher;

	public void register(RandomLoadConsumerParameters parameters) {
		dispatcher.register(parameters);
	}

	public void register(ScheduledLoadConsumerParameters parameters) {
		dispatcher.register(parameters);
	}

	public void register(PowerStationParameters parameters) {
		dispatcher.register(parameters);
	}

	public void acceptConsumerStates(List<ConsumerState> states) {
		dispatcher.acceptConsumerStates(states);
	}

	public void acceptPowerStationStates(List<PowerStationState> states) {
		dispatcher.acceptPowerStationStates(states);
	}
}
