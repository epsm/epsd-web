package com.epsm.epsdWeb.repository;

import com.epsm.epsdWeb.domain.SavedConsumerState;

public interface SavedConsumerStateDao {
	void saveState(SavedConsumerState state);	
}
