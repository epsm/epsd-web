package com.epsm.epsdweb.client;

import com.epsm.epsdweb.util.UrlRequestSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AbstractClientTest {

	@InjectMocks
	private AbstractClient<Object> client = spy(AbstractClient.class);
	
	@Mock
	private UrlRequestSender<Object> sender;
	
	@Mock
	private Object message;

	private static final String URL = "some url";

	@Test
	public void clientPassesUrlAndMessageToSender(){
		when(client.getUrl()).thenReturn(URL);
		client.send(message);
		
		verify(sender).sendObjectInJsonToUrlWithPOST(URL, message);
	}
}
