package com.epsm.epsdweb.service.chartService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class ChartsData {

	private LocalDate onDate;
	private String frequency;
	private String generationInMW;
	private String consumptionInMW;
}
