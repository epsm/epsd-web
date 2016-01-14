package com.epsm.epsdWeb.repository;

import java.util.List;

import com.epsm.epsdWeb.domain.SavedGeneratorState;

public interface SavedGeneratorStateDao {
	public List<SavedGeneratorState> getStatesByPowerStationNumber(long powerStationNumber);
	public void saveState(SavedGeneratorState state);
}
