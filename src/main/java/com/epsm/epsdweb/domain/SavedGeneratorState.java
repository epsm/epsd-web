package com.epsm.epsdweb.domain;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "generator_state")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedGeneratorState {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_state_generator_state_id_gen")
	@SequenceGenerator(
			name = "generator_state_generator_state_id_gen",
			sequenceName = "generator_state_generator_state_id_seq",
			allocationSize = 1)
	@Column(name = "generator_state_id")
	private Long generatorStateId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "power_station_state_id")
	private SavedPowerStationState savedPowerStationState;

	@Column(name = "generator_number")
	private int generatorNumber;

	@Column(name = "generation_in_mw")
	private float generationInMW;

	@Override
	public String toString() {
		return "SavedGeneratorState{" +
				"generatorStateId=" + generatorStateId +
				", savedPowerStationStateId=" + savedPowerStationState.getPowerStationId() +
				", generatorNumber=" + generatorNumber +
				", generationInMW=" + generationInMW +
				'}';
	}
}
