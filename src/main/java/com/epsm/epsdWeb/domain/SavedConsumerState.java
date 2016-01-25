package com.epsm.epsdWeb.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="consumer_state")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="id")),
	@AttributeOverride(name="powerObjectId", column=@Column(name="powerObjectId")),
	@AttributeOverride(name="realTimeStamp", column=@Column(name="realTimeStamp")),
	@AttributeOverride(name="powerObjectDate", column=@Column(name="powerObjectDate")),
	@AttributeOverride(name="powerObjectTime", column=@Column(name="powerObjectTime"))
})
public class SavedConsumerState extends SavedPowerObject{
	
	@Column(name="loadInMW")
	private float loadInMW;

	public float getLoadInMW() {
		return loadInMW;
	}

	public void setLoadInMW(float load) {
		this.loadInMW = load;
	}
}
