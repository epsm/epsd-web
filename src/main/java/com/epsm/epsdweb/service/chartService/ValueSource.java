package com.epsm.epsdweb.service.chartService;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ValueSource {

	private final double value;
	private final LocalDateTime simulationTimeStamp;
}
