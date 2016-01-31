package com.epsm.epsdWeb.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="generator_state", uniqueConstraints={
	@UniqueConstraint(columnNames={"power_object_id", "generator_number", "power_object_date",
			"power_object_time"})
})
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="id")),
	@AttributeOverride(name="powerObjectId", column=@Column(name="power_object_id")),
	@AttributeOverride(name="realTimeStamp", column=@Column(name="real_timestamp")),
	@AttributeOverride(name="powerObjectDate", column=@Column(name="power_object_date")),
	@AttributeOverride(name="powerObjectTime", column=@Column(name="power_object_time"))
})
public class SavedGeneratorState extends SavedPowerObject implements Serializable{
	private static final long serialVersionUID = 8705945525942999048L;

	@Column(name="generator_number")
	private  int generatorNumber;
	
	@Column(name="generation_in_mw")
	private float generationInMW;
	
	@Column(name="frequency")
	private float frequency;

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

	@Override
	public String toString(){
		return String.format("SavedGeneratorState: power station#%d, generator number#%d",
				getPowerObjectId(), generatorNumber);
	}
}
