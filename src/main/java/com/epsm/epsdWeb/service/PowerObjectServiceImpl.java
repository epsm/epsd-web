package com.epsm.epsdWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epsm.epsdWeb.domain.SavedConsumerState;
import com.epsm.epsdWeb.domain.SavedGeneratorState;
import com.epsm.epsdWeb.repository.SavedConsumerStateDao;
import com.epsm.epsdWeb.repository.SavedGeneratorStateDao;
import com.epsm.epsmCore.model.consumption.ConsumerState;
import com.epsm.epsmCore.model.dispatch.State;
import com.epsm.epsmCore.model.generation.PowerStationState;

@Service
@Transactional
public class PowerObjectServiceImpl implements PowerObjectService{

	@Autowired
	private SavedGeneratorStateDao generatorDao;
	
	@Autowired
	private SavedConsumerStateDao consumerDao;
	
	@Autowired
	private ConsumerStateConverter consumerConverter;
	
	@Autowired
	private PowerStationStateConverter powerStationConverter;
	
	@Override
	public void savePowerObjectState(State state) {
		if(state == null){
			String message = "State must not be null.";
			throw new IllegalArgumentException(message);
		}else if(state instanceof PowerStationState){
			saveGeneratorState((PowerStationState) state);
		}else if(state instanceof ConsumerState){
			saveConsumerState((ConsumerState) state);
		}else{
			String message = String.format("Unsuported type of State: %s.",
					state.getClass().getSimpleName());
			throw new IllegalArgumentException(message);
		}
	}
	
	private void saveGeneratorState(PowerStationState powerStationState){
		List<SavedGeneratorState> states = powerStationConverter.convert(powerStationState);
		
		for(SavedGeneratorState generatorState: states){
			generatorDao.saveState(generatorState);
		}
	}
	
	private void saveConsumerState(ConsumerState state){
		SavedConsumerState convertedState = consumerConverter.convert(state);
		consumerDao.saveState(convertedState);
	}
}
