package com.epsm.epsdweb.service;

import java.util.List;

import com.epsm.epsdcore.model.StateSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epsm.epsdweb.repository.ConsumerStateDao;
import com.epsm.epsdweb.repository.PowerStationStateDao;
import com.epsm.epsdweb.service.converter.ConsumerStateConverter;
import com.epsm.epsdweb.service.converter.PowerStationStateConverter;
import com.epsm.epsmcore.model.consumption.ConsumerState;
import com.epsm.epsmcore.model.generation.PowerStationState;

@Service
@Transactional
public class PowerObjectServiceImpl implements StateSaver {

	@Autowired
	private PowerStationStateDao generatorDao;
	
	@Autowired
	private ConsumerStateDao consumerDao;
	
	@Autowired
	private ConsumerStateConverter consumerConverter;
	
	@Autowired
	private PowerStationStateConverter powerStationConverter;
	
	@Override
	public void saveConsumerStates(List<ConsumerState> states) {
		consumerDao.saveStates(consumerConverter.convert(states));
	}

	@Override
	public void savePowerStationStates(List<PowerStationState> states) {
		generatorDao.saveStates(powerStationConverter.convert(states));
	}
}
