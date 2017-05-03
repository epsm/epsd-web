package com.epsm.epsdweb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Entity
@Table(name = "consumer_state")
public class SavedConsumerState {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consumer_state_id_gen")
	@SequenceGenerator(name = "consumer_state_id_gen", sequenceName = "consumer_state_id_seq", allocationSize = 1)
	private Long id;

	@Column(name = "power_object_id", nullable = false)
	private long powerObjectId;

	@Column(name = "simulation_timestamp", nullable = false)
	private LocalDateTime simulationTimeStamp;

	@Column(name = "real_timestamp", nullable = false)
	private LocalDateTime realTimeStamp;

	@Column(name = "load_in_mw")
	private float loadInMW;
}
