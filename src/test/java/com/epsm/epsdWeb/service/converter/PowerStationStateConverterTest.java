package com.epsm.epsdWeb.service.converter;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epsm.epsdWeb.domain.SavedGeneratorState;
import com.epsm.epsmCore.model.generation.GeneratorState;
import com.epsm.epsmCore.model.generation.PowerStationState;

public class PowerStationStateConverterTest {
	private PowerStationStateConverter converter;
	private PowerStationState powerStationState;
	private List<SavedGeneratorState> convertedStates;
	private SavedGeneratorState firstConvertedGeneratorState;
	private SavedGeneratorState secondConvertedGeneratorState;
	private final LocalDateTime REAL_TIME_TIMESTAMP = LocalDateTime.of(1, 2, 3, 4, 5, 6, 7);
	private final LocalDateTime SIMULATION_TIMESTAMP = LocalDateTime.of(7, 6, 5, 4, 3, 2, 1);
	private final int POWER_STATION_ID = 684646;
	private final int QUANTITY_OF_GENERATORS = 2;
	private final float FREQUENCY = 49.6567f;
	private final int FIRST_GENERATOR_NUMBER = 464;
	private final int SECOND_GENERATOR_NUMBER = 778;
	private final float FIRST_GENERATOR_GENERATION = 998.448f;
	private final float SECOND_GENERATOR_GENERATION = 746.885f;
	
	@Before
	public void setUp(){
		converter = new PowerStationStateConverter();
		powerStationState = new PowerStationState(POWER_STATION_ID, REAL_TIME_TIMESTAMP,
				SIMULATION_TIMESTAMP, QUANTITY_OF_GENERATORS, FREQUENCY);
		GeneratorState state_1 = new GeneratorState(FIRST_GENERATOR_NUMBER,
				FIRST_GENERATOR_GENERATION);
		GeneratorState state_2 = new GeneratorState(SECOND_GENERATOR_NUMBER,
				SECOND_GENERATOR_GENERATION);
			
		powerStationState.addGeneratorState(state_1);
		powerStationState.addGeneratorState(state_2);
		convertedStates = converter.convert(powerStationState);
		firstConvertedGeneratorState = convertedStates.get(0);
		secondConvertedGeneratorState = convertedStates.get(1);
	}
	
	@Test
	public void convertedListKeepsTheSameQuantityOfGeneratorsAsPowerStationState(){
		Assert.assertEquals(QUANTITY_OF_GENERATORS, convertedStates.size());
	}
	
	@Test
	public void firstGeneratorHasTheSamePowerStationNumberAsPowerStationState(){
		Assert.assertEquals(POWER_STATION_ID,
				firstConvertedGeneratorState.getPowerObjectId());
	}
	
	@Test
	public void firstGeneratorHasTheSameGeneratorNumberAsInPowerStationState(){
		Assert.assertEquals(FIRST_GENERATOR_NUMBER,
				firstConvertedGeneratorState.getGeneratorNumber());
	}
	
	@Test
	public void firstGeneratorHasTheSameGenerationAsInPowerStationState(){
		Assert.assertEquals(FIRST_GENERATOR_GENERATION,
				firstConvertedGeneratorState.getGenerationInMW(), 0);
	}
	
	@Test
	public void firstGeneratorHasTheSameFrequencyAsPowerStationState(){
		Assert.assertEquals(FREQUENCY,
				firstConvertedGeneratorState.getFrequency(), 0);
	}
		
	@Test
	public void firstGeneratorPowerObjectDateEqualsDateInSimulationTimeStamp(){
		Assert.assertEquals(Date.valueOf(SIMULATION_TIMESTAMP.toLocalDate()),
				firstConvertedGeneratorState.getPowerObjectDate());
	}
	
	@Test
	public void firstGeneratorPowerObjectTimeEqualsTimeInSimulationTimeStamp(){
		Assert.assertEquals(Time.valueOf(SIMULATION_TIMESTAMP.toLocalTime()),
				firstConvertedGeneratorState.getPowerObjectTime());
	}
		
	@Test
	public void firstGeneratorHasTheSameRealTimeTimeStampAsPowerStationState(){
		Assert.assertEquals(Timestamp.valueOf(REAL_TIME_TIMESTAMP),
				firstConvertedGeneratorState.getRealTimeStamp());
	}
	
	@Test
	public void secondGeneratorHasTheSamePowerStationNumberAsPowerStationState(){
		Assert.assertEquals(POWER_STATION_ID,
				secondConvertedGeneratorState.getPowerObjectId());
	}
	
	@Test
	public void secondGeneratorHasTheSameGeneratorNumberAsInPowerStationState(){
		Assert.assertEquals(SECOND_GENERATOR_NUMBER,
				secondConvertedGeneratorState.getGeneratorNumber());
	}
	
	@Test
	public void secondGeneratorHasTheSameGenerationAsInPowerStationState(){
		Assert.assertEquals(SECOND_GENERATOR_GENERATION,
				secondConvertedGeneratorState.getGenerationInMW(), 0);
	}
	
	@Test
	public void secondGeneratorHasTheSameFrequencyAsPowerStationState(){
		Assert.assertEquals(FREQUENCY,
				secondConvertedGeneratorState.getFrequency(), 0);
	}
	
	@Test
	public void secondGeneratorPowerObjectDateEqualsDateInSimulationTimeStamp(){
		Assert.assertEquals(Date.valueOf(SIMULATION_TIMESTAMP.toLocalDate()),
				secondConvertedGeneratorState.getPowerObjectDate());
	}
	
	@Test
	public void secondGeneratorPowerObjectTimeEqualsTimeInSimulationTimeStamp(){
		Assert.assertEquals(Time.valueOf(SIMULATION_TIMESTAMP.toLocalTime()),
				secondConvertedGeneratorState.getPowerObjectTime());
	}
	
	@Test
	public void secondGeneratorHasTheSameRealTimeTimeStampAsPowerStationState(){
		Assert.assertEquals(Timestamp.valueOf(REAL_TIME_TIMESTAMP),
				secondConvertedGeneratorState.getRealTimeStamp());
	}
}
