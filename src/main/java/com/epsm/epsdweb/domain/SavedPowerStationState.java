package com.epsm.epsdweb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@ToString
@Entity
@Table(name="power_station_state")
public class SavedPowerStationState extends AbstractEntity {

	@Column(name="generation_in_mw")
	private float generationInMW;
	
	@Column(name="frequency")
	private float frequency;
}
