package com.epsm.epsdWeb.domain;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SavedPowerObject {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="power_object_id")
	private  long powerObjectId;
	
	@Column(name="real_timestamp", nullable=false)
	private Timestamp realTimeStamp;
	
	@Column(name="power_object_date", nullable=false)
	private Date powerObjectDate;
	
	@Column(name="power_object_time", nullable=false)
	private Time powerObjectTime;
	
	public long getId() {
		return id;
	}
	
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

	public Date getPowerObjectDate() {
		return powerObjectDate;
	}

	public void setPowerObjectDate(Date powerObjectDate) {
		this.powerObjectDate = powerObjectDate;
	}

	public Time getPowerObjectTime() {
		return powerObjectTime;
	}

	public void setPowerObjectTime(Time powerObjectTime) {
		this.powerObjectTime = powerObjectTime;
	}
}
