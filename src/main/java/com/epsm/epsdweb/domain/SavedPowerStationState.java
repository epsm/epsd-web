package com.epsm.epsdweb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@Entity
@Table(name = "power_station_state")
public class SavedPowerStationState {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "power_station_state_id_gen")
	@SequenceGenerator(name = "power_station_state_id_gen", sequenceName = "power_station_state_id_seq", allocationSize = 1)
	private Long id;

	@Column(name = "power_object_id", nullable = false)
	private long powerObjectId;

	@Column(name="simulation_timestamp", nullable=false)
	private LocalDateTime simulationTimeStamp;

	@Column(name="real_timestamp", nullable=false)
	private LocalDateTime realTimeStamp;

	@Column(name = "generation_in_mw")
	private float generationInMW;

	@Column(name = "frequency")
	private float frequency;
}
