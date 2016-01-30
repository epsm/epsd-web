package com.epsm.epsdWeb.client;

import static org.mockito.Mockito.verify;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epsm.epsdWeb.util.UrlRequestSender;
import com.epsm.epsmCore.model.consumption.ConsumptionPermissionStub;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerClientTest {
	
	@InjectMocks
	private ConsumerClient client;
	
	@Mock
	private UrlRequestSender<ConsumptionPermissionStub> sender;
	
	@Mock
	private ConsumptionPermissionStub permission;
	
	@Before
	public void setUp() throws Exception{
		Field api = client.getClass().getDeclaredField("api");
		api.setAccessible(true);
		api.set(client, "someUrl");
	}
	
	@Test
	public void clientPassesApiAndMessageToSender(){
		client.sendConsumerPermissionToConsumer(permission);
		
		verify(sender).sendObjectInJsonToUrlWithPOST("someUrl", permission);
	}
}
