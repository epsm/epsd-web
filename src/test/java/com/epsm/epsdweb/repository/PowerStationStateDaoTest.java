package com.epsm.epsdweb.repository;

import com.epsm.epsdweb.service.chartService.ValueSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

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
}
