package com.epsm.epsdWeb.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="consumer_state", uniqueConstraints={
	@UniqueConstraint(columnNames={"power_object_id", "power_object_date", "power_object_time"})
})
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="id")),
	@AttributeOverride(name="powerObjectId", column=@Column(name="power_object_id")),
	@AttributeOverride(name="realTimeStamp", column=@Column(name="real_timestamp")),
	@AttributeOverride(name="powerObjectDate", column=@Column(name="power_object_date")),
	@AttributeOverride(name="powerObjectTime", column=@Column(name="power_object_time"))
})
public class SavedConsumerState extends SavedPowerObject implements Serializable{
	private static final long serialVersionUID = 2389021731443798690L;
	
	@Column(name="load_in_mw")
	private float loadInMW;

	public float getLoadInMW() {
		return loadInMW;
	}

	public void setLoadInMW(float load) {
		this.loadInMW = load;
	}
}
