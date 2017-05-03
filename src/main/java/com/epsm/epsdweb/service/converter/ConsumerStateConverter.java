package com.epsm.epsdweb.service.converter;

import com.epsm.epsdweb.domain.SavedConsumerState;
import com.epsm.epsmcore.model.consumption.ConsumerState;
import com.epsm.epsmcore.model.simulation.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConsumerStateConverter {

	@Autowired
	private TimeService timeService;

	public List<SavedConsumerState> convert(List<ConsumerState> source) {
		return source.stream()
				.map(this::convert)
				.collect(Collectors.toList());
	}

	public SavedConsumerState convert(ConsumerState source) {
		SavedConsumerState target = new SavedConsumerState();

		target.setPowerObjectId(source.getPowerObjectId());
		target.setRealTimeStamp(timeService.getCurrentDateTime());
		target.setSimulationTimeStamp(source.getSimulationTimeStamp());
		target.setLoadInMW(source.getLoadInMW());

		return target;
	}
}
