package com.epsm.epsdweb.service.converter;

import com.epsm.epsdweb.domain.SavedPowerStationState;
import com.epsm.epsmcore.model.generation.GeneratorState;
import com.epsm.epsmcore.model.generation.PowerStationState;
import com.epsm.epsmcore.model.simulation.TimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PowerStationStateConverterTest {


	@InjectMocks
	private PowerStationStateConverter converter;

	@Mock
	private TimeService timeService;

	private final LocalDateTime REAL_TIME_TIMESTAMP = LocalDateTime.of(1, 2, 3, 4, 5, 6, 7);
	private final LocalDateTime SIMULATION_TIMESTAMP = LocalDateTime.of(7, 6, 5, 4, 3, 2, 1);
	private final int POWER_STATION_ID = 684646;
	private final float FREQUENCY = 49.6567f;
	private final int FIRST_GENERATOR_NUMBER = 1;
	private final int SECOND_GENERATOR_NUMBER = 2;
	private final float FIRST_GENERATOR_GENERATION = 998.448f;
	private final float SECOND_GENERATOR_GENERATION = 746.885f;

	@Test
	public void setUp(){
		when(timeService.getCurrentDateTime()).thenReturn(REAL_TIME_TIMESTAMP);
		PowerStationState source = new PowerStationState(POWER_STATION_ID, SIMULATION_TIMESTAMP, FREQUENCY);
		GeneratorState state_1 = new GeneratorState(FIRST_GENERATOR_NUMBER, FIRST_GENERATOR_GENERATION);
		GeneratorState state_2 = new GeneratorState(SECOND_GENERATOR_NUMBER, SECOND_GENERATOR_GENERATION);
			
		source.getStates().put(FIRST_GENERATOR_NUMBER, state_1);
		source.getStates().put(SECOND_GENERATOR_NUMBER, state_2);
		SavedPowerStationState  actual = converter.convert(asList(source)).get(0);

		assertEquals(POWER_STATION_ID, actual.getPowerStationId());
		assertEquals(SIMULATION_TIMESTAMP, actual.getSimulationTimeStamp());
		assertEquals(FREQUENCY, actual.getFrequency(), 0);
		assertEquals(FIRST_GENERATOR_NUMBER,  actual.getGeneratorStates().get(0).getGeneratorNumber(), 0);
		assertEquals(SECOND_GENERATOR_NUMBER,  actual.getGeneratorStates().get(1).getGeneratorNumber(), 0);
		assertEquals(FIRST_GENERATOR_GENERATION, actual.getGeneratorStates().get(0).getGenerationInMW(), 0);
		assertEquals(SECOND_GENERATOR_GENERATION, actual.getGeneratorStates().get(1).getGenerationInMW(), 0);
		assertEquals(actual, actual.getGeneratorStates().get(0).getSavedPowerStationState());
		assertEquals(actual, actual.getGeneratorStates().get(1).getSavedPowerStationState());
	}
}
