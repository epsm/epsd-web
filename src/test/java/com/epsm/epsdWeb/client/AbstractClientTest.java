package com.epsm.epsdWeb.client;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epsm.epsdWeb.util.UrlRequestSender;
import com.epsm.epsmCore.model.bothConsumptionAndGeneration.Message;

@RunWith(MockitoJUnitRunner.class)
public class AbstractClientTest {
	private String url = "someUrl";
	
	@InjectMocks
	private AbstractClient<Message> client;
	
	@Mock
	private UrlRequestSender<Message> sender;
	
	@Mock
	private Message message;
	
	@Test
	public void clientPassesApiAndMessageToSender(){
		client.sendMessage(message, url);
		
		verify(sender).sendObjectInJsonToUrlWithPOST(url, message);
	}
}
