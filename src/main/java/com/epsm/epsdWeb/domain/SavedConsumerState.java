package com.epsm.epsdWeb.domain;

import java.time.LocalDateTime;

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
	
	@Column(name="simulationTimeStamp")
	private LocalDateTime simulationTimeStamp;
	
	@Column(name="realTimeStamp")
	private LocalDateTime realTimeStamp;

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

	public LocalDateTime getSimulationTimeStamp() {
		return simulationTimeStamp;
	}

	public void setSimulationTimeStamp(LocalDateTime simulationTimeStamp) {
		this.simulationTimeStamp = simulationTimeStamp;
	}

	public LocalDateTime getRealTimeStamp() {
		return realTimeStamp;
	}

	public void setRealTimeStamp(LocalDateTime realTimeStamp) {
		this.realTimeStamp = realTimeStamp;
	}

	public long getId() {
		return id;
	}
}
