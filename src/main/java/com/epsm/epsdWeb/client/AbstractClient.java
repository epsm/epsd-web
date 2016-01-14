package com.epsm.epsdWeb.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.epsm.epsdWeb.util.UrlRequestSender;
import com.epsm.epsmCore.model.bothConsumptionAndGeneration.Message;

public class AbstractClient<T extends Message> {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UrlRequestSender<T> sender;
	
	protected void sendMessage(T message, String url){
		logger.debug("sending {} to {}.", message, url);
		
		sender.sendObjectInJsonToUrlWithPOST(url, message);
	}
}
