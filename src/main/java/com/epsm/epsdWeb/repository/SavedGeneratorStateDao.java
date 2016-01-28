package com.epsm.epsdWeb.repository;

import com.epsm.epsdWeb.domain.SavedGeneratorState;

public interface SavedGeneratorStateDao {
	void saveState(SavedGeneratorState state);
}