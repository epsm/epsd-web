package com.epsm.epsdWeb.repository;

import java.time.LocalDate;

import com.epsm.epsdWeb.domain.SavedGeneratorState;

public interface SavedGeneratorStateDao {
	LocalDate getLastEntryDate();
	void saveState(SavedGeneratorState state);
}