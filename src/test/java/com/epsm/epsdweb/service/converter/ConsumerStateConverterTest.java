package com.epsm.epsdweb.service.converter;

import com.epsm.epsdweb.domain.SavedConsumerState;
import com.epsm.epsmcore.model.consumption.ConsumerState;
import com.epsm.epsmcore.model.consumption.ConsumerType;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class ConsumerStateConverterTest {

	private ConsumerStateConverter converter = new ConsumerStateConverter();

	private final int CONSUMER_ID = 684646;
	private final float LOAD = 49.6567f;
	private final LocalDateTime SIMULATION_TIMESTAMP = LocalDateTime.of(7, 6, 5, 4, 3, 2, 1);

	@Test
	public void loadsAreEquals(){
		ConsumerState source = new ConsumerState(CONSUMER_ID, SIMULATION_TIMESTAMP, LOAD, ConsumerType.SCHEDULED_LOAD);

		SavedConsumerState actual = converter.convert(source);

		assertEquals(CONSUMER_ID, actual.getConsumerId());
		assertEquals(LOAD, actual.getLoadInMW(), 0);
		assertEquals(SIMULATION_TIMESTAMP, actual.getSimulationTimeStamp());
	}
}
