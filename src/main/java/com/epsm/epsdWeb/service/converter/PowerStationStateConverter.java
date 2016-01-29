package com.epsm.epsdWeb.service.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.epsm.epsdWeb.domain.SavedGeneratorState;
import com.epsm.epsmCore.model.generation.GeneratorState;
import com.epsm.epsmCore.model.generation.PowerStationState;

@Component("powstConverter")//name for WildFly, on Tomcat no problem
public class PowerStationStateConverter extends SavedPowerObjectConverter<PowerStationState>{
	private List<SavedGeneratorState> convertedStates;
	private float frequency;
	private int generatorNumber;
	private float generationInMW;
	private SavedGeneratorState convertedState;
	private Logger logger = LoggerFactory.getLogger(PowerStationStateConverter.class);
	
	public PowerStationStateConverter() {
		convertedStates = new ArrayList<SavedGeneratorState>();
	}
	
	public synchronized List<SavedGeneratorState> convert(PowerStationState powerStationState) {
		saveSource(powerStationState);
		eraseConvertedStates();
		getGeneralFields();
		getFrequencyField();
		createSavedGeneratorStates();
		logger.debug("Converted: PowerStationState to SavedGeneratorState.");

		
		return convertedStates;
	}
	
	private void eraseConvertedStates(){
		convertedStates.clear();
	}
	
	private void getFrequencyField(){
		frequency = source.getFrequency();
	}
	
	private void createSavedGeneratorStates(){
		Set<Integer> generatorNumbers = source.getGeneratorsNumbers();
		
		for(int generator: generatorNumbers){
			GeneratorState generatorState = source.getGeneratorState(generator);
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
		
		fillGeneralFields(convertedState);
		convertedState.setGeneratorNumber(generatorNumber);
		convertedState.setGenerationInMW(generationInMW);
		convertedState.setFrequency(frequency);
	}
	
	private void addSavedGeneratorStateToList(){
		convertedStates.add(convertedState);
	}
}
