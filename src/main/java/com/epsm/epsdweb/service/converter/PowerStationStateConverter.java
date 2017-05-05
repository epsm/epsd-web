package com.epsm.epsdweb.service.converter;

import com.epsm.epsdweb.domain.SavedGeneratorState;
import com.epsm.epsdweb.domain.SavedPowerStationState;
import com.epsm.epsmcore.model.generation.GeneratorState;
import com.epsm.epsmcore.model.generation.PowerStationState;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PowerStationStateConverter {

	public List<SavedPowerStationState> convert(List<PowerStationState> source) {
		return source.stream()
				.map(this::convert)
				.collect(Collectors.toList());
	}

	private SavedPowerStationState convert(PowerStationState source) {
		SavedPowerStationState target = SavedPowerStationState.builder()
				.powerStationId(source.getPowerObjectId())
				.simulationTimeStamp(source.getSimulationTimeStamp())
				.frequency(source.getFrequency())
				.build();

		target.setGeneratorStates(convert(source.getStates().values(), target));

		return target;
	}

	private List<SavedGeneratorState> convert(Collection<GeneratorState> source, SavedPowerStationState powerStationState) {
		return source.stream()
				.map(s -> convert(s, powerStationState))
				.collect(Collectors.toList());
	}

	private SavedGeneratorState convert(GeneratorState source, SavedPowerStationState  powerStationState) {
		return SavedGeneratorState.builder()
				.generatorNumber(source.getGeneratorNumber())
				.savedPowerStationState(powerStationState)
				.generationInMW(source.getGenerationInWM())
				.build();
	}
}
