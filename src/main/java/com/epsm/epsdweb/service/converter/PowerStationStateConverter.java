package com.epsm.epsdweb.service.converter;

import com.epsm.epsdweb.domain.SavedPowerStationState;
import com.epsm.epsmcore.model.generation.GeneratorState;
import com.epsm.epsmcore.model.generation.PowerStationState;
import com.epsm.epsmcore.model.simulation.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PowerStationStateConverter {

	@Autowired
	private TimeService timeService;

	public List<SavedPowerStationState> convert(List<PowerStationState> source) {
		return source.stream()
				.map(this::convert)
				.collect(Collectors.toList());
	}

	private SavedPowerStationState convert(PowerStationState source) {
		SavedPowerStationState target = new SavedPowerStationState();
		float totalGeneration = 0;

		for(GeneratorState state: source.getStates().values()) {
			totalGeneration += state.getGenerationInWM();
		}

		target.setPowerObjectId(source.getPowerObjectId());
		target.setRealTimeStamp(timeService.getCurrentDateTime());
		target.setSimulationTimeStamp(source.getSimulationTimeStamp());
		target.setFrequency(source.getFrequency());
		target.setGenerationInMW(totalGeneration);

		return target;
	}
}
