package com.epsm.epsdWeb.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="consumer_state")
public class SavedConsumerState{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="consumerId")
	private long consumerId;
	
	@Column(name="loadInMW")
	private float loadInMW;
	
	@Column(name="realTimeStamp")
	private LocalDateTime realTimeStamp;

	@Column(name="powerObjectDate")
	private LocalDate powerObjectDate;
	
	@Column(name="powerObjectTime")
	private LocalTime powerObjectTime;
	
	public long getId() {
		return id;
	}
	
	public long getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(long consumerId) {
		this.consumerId = consumerId;
	}

	public float getLoadInMW() {
		return loadInMW;
	}

	public void setLoadInMW(float load) {
		this.loadInMW = load;
	}

	public LocalDateTime getRealTimeStamp() {
		return realTimeStamp;
	}

	public void setRealTimeStamp(LocalDateTime realTimeStamp) {
		this.realTimeStamp = realTimeStamp;
	}

	public LocalDate getPowerObjectDate() {
		return powerObjectDate;
	}

	public void setPowerObjectDate(LocalDate powerObjectDate) {
		this.powerObjectDate = powerObjectDate;
	}

	public LocalTime getPowerObjectTime() {
		return powerObjectTime;
	}

	public void setPowerObjectTime(LocalTime powerObjectTime) {
		this.powerObjectTime = powerObjectTime;
	}
}
