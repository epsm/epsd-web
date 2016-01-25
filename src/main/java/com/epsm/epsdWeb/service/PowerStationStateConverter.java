package com.epsm.epsdWeb.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.epsm.epsdWeb.domain.SavedGeneratorState;
import com.epsm.epsmCore.model.generation.GeneratorState;
import com.epsm.epsmCore.model.generation.PowerStationState;

@Component
public class PowerStationStateConverter {
	private PowerStationState powerStationState;
	private List<SavedGeneratorState> convertedStates;
	private long powerStationId;
	private float frequency;
	private LocalDateTime simulationTimeStamp;
	private LocalDateTime realTimeStamp;
	private int generatorNumber;
	private float generationInMW;
	private SavedGeneratorState convertedState;
	
	public PowerStationStateConverter() {
		convertedStates = new ArrayList<SavedGeneratorState>();
	}
	
	public synchronized List<SavedGeneratorState> convert(PowerStationState powerStationState) {
		saveState(powerStationState);
		erseConvertedStates();
		getGeneralFields();
		createSavedGeneratorStates();
		
		return convertedStates;
	}
	
	private void saveState(PowerStationState powerStationState){
		this.powerStationState = powerStationState;
	}
	
	private void erseConvertedStates(){
		convertedStates.clear();
	}
	
	private void getGeneralFields(){
		powerStationId = powerStationState.getPowerObjectId();
		frequency = powerStationState.getFrequency();
		simulationTimeStamp = powerStationState.getSimulationTimeStamp();
		realTimeStamp = powerStationState.getRealTimeStamp();
	}
	
	private void createSavedGeneratorStates(){
		Set<Integer> generatorNumbers = powerStationState.getGeneratorsNumbers();
		
		for(int generator: generatorNumbers){
			GeneratorState generatorState = powerStationState.getGeneratorState(generator);
			getDataForConcreteGenerator(generatorState);
			createSavedGeneratorState();
			fillOutConcreteSavedGeneratorState();
			addSavedGeneratorStateToList();
		}
	}
	
	private void getDataForConcreteGenerator(GeneratorState generatorState){
		generatorNumber = generatorState.getGeneratorNumber();
		generationInMW = generatorState.getGenerationInWM();
	}
	
	private void createSavedGeneratorState(){
		convertedState = new SavedGeneratorState();
	}
	
	private void fillOutConcreteSavedGeneratorState(){
		convertedState = new SavedGeneratorState();
		convertedState.setPowerStationId(powerStationId);
		convertedState.setGeneratorNumber(generatorNumber);
		convertedState.setGenerationInMW(generationInMW);
		convertedState.setFrequency(frequency);
		convertedState.setPowerObjectDate(simulationTimeStamp.toLocalDate());
		convertedState.setPowerObjectTime(simulationTimeStamp.toLocalTime());
		convertedState.setRealTimeStamp(realTimeStamp);
	}
	
	private void addSavedGeneratorStateToList(){
		convertedStates.add(convertedState);
	}
}
