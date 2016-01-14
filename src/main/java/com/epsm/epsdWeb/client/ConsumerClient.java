package com.epsm.epsdWeb.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.epsm.epsmCore.model.consumption.ConsumptionPermissionStub;

@Component
public class ConsumerClient extends AbstractClient<ConsumptionPermissionStub>{
	
	@Value("${api.consumer.command}")
	private String api;
	
	public void sendConsumerPermissionToConsumer(ConsumptionPermissionStub permission){
		sendMessage(permission, api);
	}
}
