package com.epsm.epsdweb.client;

import com.epsm.epsmcore.model.consumption.ConsumerPermission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConsumerClient extends AbstractClient<ConsumerPermission>{
	
	@Value("${api.consumer.command}")
	private String url;
	
	@Override
	protected String getUrl() {
		return url;
	}
}
