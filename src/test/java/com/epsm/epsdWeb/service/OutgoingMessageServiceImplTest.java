package com.epsm.epsdWeb.service;

import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epsm.epsdWeb.client.ConsumerClient;
import com.epsm.epsdWeb.client.PowerStationClient;
import com.epsm.epsmCore.model.consumption.ConsumptionPermissionStub;
import com.epsm.epsmCore.model.dispatch.Command;
import com.epsm.epsmCore.model.generation.PowerStationGenerationSchedule;

@RunWith(MockitoJUnitRunner.class)
public class OutgoingMessageServiceImplTest {
	private PowerStationGenerationSchedule schedule;
	private ConsumptionPermissionStub permission;
	
	@InjectMocks
	private OutgoingMessageServiceImpl service;
	
	@Mock
	private ConsumerClient consumerClient;
	
	@Mock
	private PowerStationClient powerStationClient;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void exceptionIfCommandisNull(){
		expectedEx.expect(IllegalArgumentException.class);
	    expectedEx.expectMessage("Command must not be null.");
		
		service.sendCommand(null);
	}
	
	@Test
	public void sendsPowerStationGenerationScheduleWithScheduleSender(){
		schedule = new PowerStationGenerationSchedule(1, LocalDateTime.MIN, LocalDateTime.MIN, 1);
		
		service.sendCommand(schedule);
		
		verify(powerStationClient).sendGenerationScheduleToPowerStation(schedule);
	}
	
	@Test
	public void sendsPowerConsumerPermissioneWithPermissionSender(){
		permission = new ConsumptionPermissionStub(1, LocalDateTime.MIN, LocalDateTime.MIN);
		
		service.sendCommand(permission);
		
		verify(consumerClient).sendConsumerPermissionToConsumer(permission);
	}
	
	@Test
	public void exceptionIfCommandUnsuported(){
		expectedEx.expect(IllegalArgumentException.class);
	    expectedEx.expectMessage("Unsuported type of Command: UnsupportedCommand.");
		
		service.sendCommand(new UnsupportedCommand(1, LocalDateTime.MIN, LocalDateTime.MIN));
	}
	
	private class UnsupportedCommand extends Command{
		public UnsupportedCommand(long powerObjectId, LocalDateTime realTimeStamp,
				LocalDateTime simulationTimeStamp) {
			
			super(powerObjectId, realTimeStamp, simulationTimeStamp);
		}

		@Override
		public String toString() {
			return null;
		}
	}
}
