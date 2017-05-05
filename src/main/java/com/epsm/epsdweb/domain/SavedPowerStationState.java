package com.epsm.epsdweb.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@ToString
@Entity
@Table(name = "power_station_state")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedPowerStationState {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "power_station_state_power_station_state_id_gen")
	@SequenceGenerator(
			name = "power_station_state_power_station_state_id_gen",
			sequenceName = "power_station_state_power_station_state_id_seq",
			allocationSize = 1)
	@Column(name = "power_station_state_id")
	private Long powerStationStateId;

	@Column(name = "power_station_id", nullable = false)
	private long powerStationId;

	@Column(name="simulation_timestamp", nullable=false)
	private LocalDateTime simulationTimeStamp;

	@Column(name = "frequency")
	private double frequency;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "power_station_state_id")
	private List<SavedGeneratorState> generatorStates;
}
