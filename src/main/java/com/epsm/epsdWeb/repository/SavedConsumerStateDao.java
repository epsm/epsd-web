package com.epsm.epsdWeb.repository;

import java.util.List;

import com.epsm.epsdWeb.domain.SavedConsumerState;

public interface SavedConsumerStateDao {
	public List<SavedConsumerState> getStatesByNumber(long consumerNumber);
	public void saveState(SavedConsumerState state);
}
