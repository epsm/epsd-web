package com.epsm.epsdWeb.client;

import org.springfrmework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import com.epsm.epsmCore.model.bothConsumptionAndGeneration.Message;
import com.epsm.epsmWeb.util.UrlRequestSender;

@Import(UrlRequestSender.class)
public class AbstractClient<T extends Message> {

	@Autowired
	private UrlRequestSender<T> sender;
	
	protected void sendMessage(T message, String url){
		sender.sendObjectInJsonToUrlWithPOST(url, message);
	}
}
