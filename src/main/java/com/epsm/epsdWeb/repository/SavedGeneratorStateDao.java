package com.epsm.epsdWeb.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.epsm.epsdWeb.domain.SavedGeneratorState;

public interface SavedGeneratorStateDao {
	LocalDate getLastEntryDate();
	List<SavedGeneratorState> getStates(LocalDate date);
	List<SavedGeneratorState> getStates(LocalDate date, long powerObjectId, int generatorNumber);
	List<Long> getPowerObjectsIds(LocalDate date);
	List<Integer> getGeneratorsNumbers(LocalDate date, long powerObjectId);
	Float getFrequency(LocalDateTime date, long powerObjectId,int generatorNumber);
	void saveState(SavedGeneratorState state);
}