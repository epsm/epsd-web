package com.epsm.epsdWeb.service;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epsm.epsmCore.model.consumption.ConsumerParametersStub;
import com.epsm.epsmCore.model.consumption.ConsumerState;
import com.epsm.epsmCore.model.dispatch.Dispatcher;
import com.epsm.epsmCore.model.generation.PowerStationParameters;
import com.epsm.epsmCore.model.generation.PowerStationState;

@RunWith(MockitoJUnitRunner.class)
public class IncominMessageServiceImplTest {
	private ConsumerParametersStub consumerParameters;
	private ConsumerState consumerState;
	private PowerStationParameters stationParameters;
	private PowerStationState stationState;
	private final LocalDateTime START_DATE_TIME = LocalDateTime.MIN;
	private final LocalDateTime OBJECT_DATE_TIME = LocalDateTime.MAX; 
	
	@Mock
	private Dispatcher dispatcher;
	
	@InjectMocks
	private IncomingMessageServiceImpl service;
	
	@Before
	public void setUp(){
		consumerParameters = new ConsumerParametersStub(0, START_DATE_TIME, OBJECT_DATE_TIME);
		consumerState = new ConsumerState(0, START_DATE_TIME, OBJECT_DATE_TIME, 0);
		stationParameters = new PowerStationParameters(0, START_DATE_TIME, OBJECT_DATE_TIME, 1);
		stationState = new PowerStationState(0, START_DATE_TIME, OBJECT_DATE_TIME, 1, 0);
	}
	
	@Test
	public void getPowerObjectsDateTimeMethodReturnsLocalDateTimeMinOriginally(){
		Assert.assertEquals(START_DATE_TIME, service.getPowerObjectsDateTime());
	}
	
	@Test
	public void registerConsumerMethodUpdatesDate(){
		service.registerConsumer(consumerParameters);
		
		Assert.assertEquals(OBJECT_DATE_TIME, service.getPowerObjectsDateTime());
	}
	
	@Test
	public void acceptConsumerStateMethodUpdatesDate(){
		service.acceptConsumerState(consumerState);
		
		Assert.assertEquals(OBJECT_DATE_TIME, service.getPowerObjectsDateTime());
	}
	
	@Test
	public void registerPowerStationMethodUpdatesDate(){
		service.registerPowerStation(stationParameters);
		
		Assert.assertEquals(OBJECT_DATE_TIME, service.getPowerObjectsDateTime());
	}
	
	@Test
	public void acceptPowerStationStateMethodUpdatesDate(){
		service.acceptPowerStationState(stationState);
		
		Assert.assertEquals(OBJECT_DATE_TIME, service.getPowerObjectsDateTime());
	}
	
	@Test
	public void timeDoesNotUpdateInBackDirection(){
		updateTimeForwardThenBackward();
		
		Assert.assertEquals(OBJECT_DATE_TIME, service.getPowerObjectsDateTime());
	}
	
	private void updateTimeForwardThenBackward(){
		service.acceptPowerStationState(stationState);
		stationState = new PowerStationState(0, START_DATE_TIME, START_DATE_TIME, 1, 0);
		service.acceptPowerStationState(stationState);
	}
}
