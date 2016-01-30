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
import com.epsm.epsmCore.model.generation.PowerStationGenerationSchedule;

@RunWith(MockitoJUnitRunner.class)
public class PowerStationClientTest {
	
	@InjectMocks
	private PowerStationClient client;
	
	@Mock
	private UrlRequestSender<PowerStationGenerationSchedule> sender;
	
	@Mock
	private PowerStationGenerationSchedule schedule;
	
	@Before
	public void setUp() throws Exception{
		Field api = client.getClass().getDeclaredField("api");
		api.setAccessible(true);
		api.set(client, "someUrl");
	}
	
	@Test
	public void clientPassesApiAndMessageToSender(){
		client.sendGenerationScheduleToPowerStation(schedule);
		
		verify(sender).sendObjectInJsonToUrlWithPOST("someUrl", schedule);
	}
}
