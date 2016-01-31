package com.epsm.epsdWeb.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="frequency")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="id")),
	@AttributeOverride(name="powerObjectDate", column=@Column(name="power_object_date")),
	@AttributeOverride(name="powerObjectTime", column=@Column(name="power_object_time"))
})
public class Frequency extends SavedEntity implements ValueSource, Serializable{
	private static final long serialVersionUID = 2015385664168021608L;
	
	@Column(name="frequency", updatable=false)
	private float frequency;

	@Override
	public float getValue() {
		return frequency;
	}
}
