package com.epsm.epsdWeb.service.converter;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.epsm.epsdWeb.domain.SavedPowerObject;
import com.epsm.epsmCore.model.dispatch.State;

public abstract class SavedPowerObjectConverter<T extends State> {
	protected T source;
	protected long powerObjectId;
	protected LocalDateTime simulationTimeStamp;
	protected LocalDateTime realTimeStamp;
	
	protected void saveSource(T source){
		this.source = source;
	}
	
	protected void getGeneralFields(){
		powerObjectId = source.getPowerObjectId();
		simulationTimeStamp = source.getSimulationTimeStamp();
		realTimeStamp = source.getRealTimeStamp();
	}
	
	protected void fillGeneralFields(SavedPowerObject object){
		object.setPowerObjectId(powerObjectId);
		object.setPowerObjectDate(Date.valueOf(simulationTimeStamp.toLocalDate()));
		object.setPowerObjectTime(Time.valueOf(simulationTimeStamp.toLocalTime()));
		object.setRealTimeStamp(Timestamp.valueOf(realTimeStamp));
	}
}
