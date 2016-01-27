package com.epsm.epsdWeb.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SavedPowerObject extends SavedEntity{

	@Column(name="power_object_id")
	private  long powerObjectId;
	
	@Column(name="real_timestamp", nullable=false)
	private Timestamp realTimeStamp;
	
	public long getPowerObjectId() {
		return powerObjectId;
	}

	public void setPowerObjectId(long powerObjectId) {
		this.powerObjectId = powerObjectId;
	}
	
	public Timestamp getRealTimeStamp() {
		return realTimeStamp;
	}

	public void setRealTimeStamp(Timestamp realTimeStamp) {
		this.realTimeStamp = realTimeStamp;
	}
}
