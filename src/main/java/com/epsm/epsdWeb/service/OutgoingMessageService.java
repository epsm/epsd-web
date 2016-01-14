package com.epsm.epsdWeb.service;

import com.epsm.epsmCore.model.dispatch.Command;

public interface OutgoingMessageService {
	void sendCommand(Command command);
}
