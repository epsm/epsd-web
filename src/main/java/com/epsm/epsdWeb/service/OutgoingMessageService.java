package com.epsm.epsdWeb.service;

import com.epsm.epsdCore.model.ObjectsConnector;
import com.epsm.epsmCore.model.dispatch.Command;

public interface OutgoingMessageService extends ObjectsConnector{
	void sendCommand(Command command);
}
