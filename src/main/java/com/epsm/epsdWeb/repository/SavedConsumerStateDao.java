package com.epsm.epsdWeb.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.epsm.epsdWeb.domain.SavedConsumerState;

public interface SavedConsumerStateDao {
	Date getLastSaveDate();
	List<SavedConsumerState> getStatesOnDate(LocalDate date);
	void saveState(SavedConsumerState state);	
}
