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
@Table(name="generator_state")
public class SavedGeneratorState{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="powerStationId")
	private  long powerStationId;
	
	@Column(name="generator_number")
	private  int generatorNumber;
	
	@Column(name="generation")
	private float generationInMW;
	
	@Column(name="frequency")
	private float frequency;

	@Column(name="realTimeStamp")
	private LocalDateTime realTimeStamp;
	
	@Column(name="powerObjectDate")
	private LocalDate powerObjectDate;
	
	@Column(name="powerObjectTime")
	private LocalTime powerObjectTime;
	
	public long getId() {
		return id;
	}
	
	public long getPowerStationId() {
		return powerStationId;
	}

	public void setPowerStationId(long powerStationId) {
		this.powerStationId = powerStationId;
	}

	public int getGeneratorNumber() {
		return generatorNumber;
	}

	public void setGeneratorNumber(int generatorNumber) {
		this.generatorNumber = generatorNumber;
	}

	public float getGenerationInMW() {
		return generationInMW;
	}

	public void setGenerationInMW(float generationInMW) {
		this.generationInMW = generationInMW;
	}

	public float getFrequency() {
		return frequency;
	}

	public void setFrequency(float frequency) {
		this.frequency = frequency;
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

	@Override
	public String toString(){
		return String.format("SavedGeneratorState toString() stub. p.st.#%d, gen.#%d",
				powerStationId, generatorNumber);
	}
}
