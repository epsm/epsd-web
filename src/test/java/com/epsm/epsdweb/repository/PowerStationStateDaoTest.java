package com.epsm.epsdweb.repository;

import com.epsm.epsdweb.domain.SavedGeneratorState;
import com.epsm.epsdweb.domain.SavedPowerStationState;
import com.epsm.epsdweb.service.chartService.ValueSource;
import com.epsm.epsdweb.service.converter.PowerStationStateConverter;
import com.epsm.epsmcore.model.generation.GeneratorState;
import com.epsm.epsmcore.model.generation.PowerStationState;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class PowerStationStateDaoTest extends AbstractDaoTest {

	private static final LocalDateTime DATE_2010_01_01 = LocalDateTime.of(2010, 1, 1, 0, 0);
	private static final LocalDateTime DATE_2015_01_01 = LocalDateTime.of(2015, 1, 1, 0, 0);
	private static final LocalDateTime DATE_2020_01_01 = LocalDateTime.of(2020, 1, 1, 0, 0);
	private static final double EXPECTED_FREQUENCY_2010_01_01 = 11;
	private static final double EXPECTED_FREQUENCY_2015_01_01 = 12;
	private static final double DELTA = 0.0000000001;

	@Autowired
	private PowerStationStateDao powerStationStateDao;
	
	@Test
	public void getFRequencyReturnsConsumptionBetweenDateTimes(){
		LocalDateTime from = DATE_2010_01_01;
		LocalDateTime to = DATE_2020_01_01;

		List<ValueSource> actual = powerStationStateDao.getFrequency(from, to);

		assertEquals(2, actual.size());
		assertEquals(DATE_2010_01_01, actual.get(0).getSimulationTimeStamp());
		assertEquals(DATE_2015_01_01, actual.get(1).getSimulationTimeStamp());
		assertEquals(EXPECTED_FREQUENCY_2010_01_01, actual.get(0).getValue(), DELTA);
		assertEquals(EXPECTED_FREQUENCY_2015_01_01, actual.get(1).getValue(), DELTA);
	}


	@Autowired
	private GeneratorStateDao generatorStateDao;


	@Test
	public void sss() {
		SavedPowerStationState savedPowerStationState = SavedPowerStationState.builder()
				.powerStationStateId(1L)
				.build();

		SavedGeneratorState state_1 = SavedGeneratorState.builder()
				.savedPowerStationState(savedPowerStationState)
				.generationInMW(3)
				.build();

		SavedGeneratorState state_2 = SavedGeneratorState.builder()
				.savedPowerStationState(savedPowerStationState)
				.generationInMW(3)
				.build();


		generatorStateDao.saveStates(asList(state_1, state_2));
	}

	@Test
	public void sssfff() {
		PowerStationState powerStationState = new PowerStationState(1, LocalDateTime.now(), 13.4f);

		GeneratorState generatorState_1 = new GeneratorState(1, 2f);
		GeneratorState generatorState_2 = new GeneratorState(2, 3f);

		powerStationState.getStates().put(1, generatorState_1);
		powerStationState.getStates().put(2, generatorState_2);

		List<PowerStationState> powerStationStateList = asList(powerStationState);

		List<SavedPowerStationState> savedPowerStationStateList = new PowerStationStateConverter().convert(powerStationStateList);

		powerStationStateDao.saveStates(savedPowerStationStateList);
	}
}
