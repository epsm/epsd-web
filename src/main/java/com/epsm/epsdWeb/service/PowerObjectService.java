package com.epsm.epsdWeb.service;

import com.epsm.epsdCore.model.StateSaver;
import com.epsm.epsmCore.model.dispatch.State;

public interface PowerObjectService extends StateSaver{
	void savePowerObjectState(State state);
}
