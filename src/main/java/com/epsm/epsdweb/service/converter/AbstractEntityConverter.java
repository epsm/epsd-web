package com.epsm.epsdweb.service.converter;

import com.epsm.epsdweb.domain.AbstractEntity;
import com.epsm.epsmcore.model.common.State;
import com.epsm.epsmcore.model.simulation.TimeService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractEntityConverter<T extends State> {

	@Autowired
	protected TimeService timeService;

	protected AbstractEntity fillCommonFields(AbstractEntity object, T source){
		object.setPowerObjectId(source.getPowerObjectId());
		object.setSimulationTimeStamp(source.getSimulationTimeStamp());
		object.setRealTimeStamp(timeService.getCurrentDateTime());

		return object;
	}
}
