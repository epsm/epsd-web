package com.epsm.epsdWeb.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="generator_state")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="id")),
	@AttributeOverride(name="powerObjectId", column=@Column(name="powerObjectId")),
	@AttributeOverride(name="realTimeStamp", column=@Column(name="realTimeStamp")),
	@AttributeOverride(name="powerObjectDate", column=@Column(name="powerObjectDate")),
	@AttributeOverride(name="powerObjectTime", column=@Column(name="powerObjectTime"))
})
public class SavedGeneratorState extends SavedPowerObject{
	
	@Column(name="generator_number")
	private  int generatorNumber;
	
	@Column(name="generation")
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
		return String.format("SavedGeneratorState toString() stub. p.st.#%d, gen.#%d",
				getPowerObjectId(), generatorNumber);
	}
}
