package com.epsm.epsdweb.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.epsm.epsdweb.util.UrlRequestSender;

public abstract class AbstractClient<T> {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UrlRequestSender<T> sender;
	
	public void send(T message){
		logger.debug("Sending: {} to {}.", message, getUrl());
		
		sender.sendObjectInJsonToUrlWithPOST(getUrl(), message);
	}

	protected abstract String getUrl();
}
