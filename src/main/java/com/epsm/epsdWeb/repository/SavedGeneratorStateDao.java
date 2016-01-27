package com.epsm.epsdWeb.repository;

import java.time.LocalDate;
import java.util.List;

import com.epsm.epsdWeb.domain.SavedGeneratorState;

public interface SavedGeneratorStateDao {
	LocalDate getLastEntryDate();
	List<SavedGeneratorState> getStatesOnDate(LocalDate date);
	List<Long> getPowerObjectsIdsOnDate(LocalDate date);
	List<Integer> getGeneratorsNumbersOnDateForPowerStation(LocalDate date, long powerObjectId);
	List<SavedGeneratorState> getStatesOnDateForPowerStationAndGenerator(
			LocalDate date, long powerObjectId, int generatorNumber);
	Float getMidnightFrequencyOnDateForPowerStationAndGenerator(LocalDate date, long powerObjectId,
			int generatorNumber);
	void saveState(SavedGeneratorState state);
}