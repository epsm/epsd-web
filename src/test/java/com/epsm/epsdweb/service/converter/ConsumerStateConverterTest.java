package com.epsm.epsdweb.service.converter;

import com.epsm.epsdweb.domain.SavedConsumerState;
import com.epsm.epsmcore.model.consumption.ConsumerState;
import com.epsm.epsmcore.model.consumption.ConsumerType;
import com.epsm.epsmcore.model.simulation.TimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerStateConverterTest {

	@InjectMocks
	private ConsumerStateConverter converter;

	@Mock
	private TimeService timeService;

	private final int CONSUMER_ID = 684646;
	private final float LOAD = 49.6567f;
	private final LocalDateTime REAL_TIME_TIMESTAMP = LocalDateTime.of(1, 2, 3, 4, 5, 6, 7);
	private final LocalDateTime SIMULATION_TIMESTAMP = LocalDateTime.of(7, 6, 5, 4, 3, 2, 1);

	@Test
	public void loadsAreEquals(){
		when(timeService.getCurrentDateTime()).thenReturn(REAL_TIME_TIMESTAMP);
		ConsumerState source = new ConsumerState(CONSUMER_ID, SIMULATION_TIMESTAMP, LOAD, ConsumerType.SCHEDULED_LOAD);

		SavedConsumerState actual = converter.convert(source);

		assertEquals(CONSUMER_ID, actual.getPowerObjectId());
		assertEquals(LOAD, actual.getLoadInMW(), 0);
		assertEquals(REAL_TIME_TIMESTAMP, actual.getRealTimeStamp());
		assertEquals(SIMULATION_TIMESTAMP, actual.getSimulationTimeStamp());

	}
}
