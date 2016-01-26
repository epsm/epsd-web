package com.epsm.epsdWeb.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.epsm.epsdWeb.domain.SavedGeneratorState;

public interface SavedGeneratorStateDao {
	Date getLastSavedDate();
	List<SavedGeneratorState> getStatesOnDate(LocalDate date);
	List<Long> getPowerObjectsIdsOnDate(LocalDate date);
	List<Integer> getGeneratorsNumbersOnDateForPowerStation(LocalDate date, long powerObjectId);
	List<SavedGeneratorState> getGeneratorStatesOnDateForPowerStation(LocalDate date, long powerObjectId);
	void saveState(SavedGeneratorState state);
}
