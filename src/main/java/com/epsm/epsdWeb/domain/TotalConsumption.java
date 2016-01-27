package com.epsm.epsdWeb.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.epsm.epsdWeb.service.chartDataService.ValueSource;

@Entity
@Table(name="total_consumption")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="id")),
	@AttributeOverride(name="powerObjectDate", column=@Column(name="power_object_date")),
	@AttributeOverride(name="powerObjectTime", column=@Column(name="power_object_time"))
})
public class TotalConsumption extends SavedEntity implements ValueSource, Serializable{
	private static final long serialVersionUID = -5513413188589243774L;
	
	@Column(name="total_consumption_in_mw")
	private float totalConsumptionInMW;

	public float getTotalConsumptionInMW() {
		return totalConsumptionInMW;
	}

	public void setTotalConsumptionInMW(float totalConsumptionInMW) {
		this.totalConsumptionInMW = totalConsumptionInMW;
	}

	@Override
	public float getValue() {
		return totalConsumptionInMW;
	}
}
