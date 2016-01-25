package com.epsm.epsdWeb.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.epsm.epsdWeb.domain.SavedGeneratorState;

public interface SavedGeneratorStateDao {
	Date getLastSaveDate();
	List<SavedGeneratorState> getStatesOnDate(LocalDate date);
	void saveState(SavedGeneratorState state);
}
