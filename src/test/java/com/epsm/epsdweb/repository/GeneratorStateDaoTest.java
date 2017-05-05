package com.epsm.epsdweb.repository;

import com.epsm.epsdweb.service.chartService.ValueSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeneratorStateDaoTest extends AbstractDaoTest {

	private static final LocalDateTime DATE_2010_01_01 = LocalDateTime.of(2010, 1, 1, 0, 0);
	private static final LocalDateTime DATE_2015_01_01 = LocalDateTime.of(2015, 1, 1, 0, 0);
	private static final LocalDateTime DATE_2020_01_01 = LocalDateTime.of(2020, 1, 1, 0, 0);
	private static final double EXPECTED_GENERATION_2010_01_01 = 11.1 + 12.2;
	private static final double EXPECTED_GENERATION_2015_01_01 = 13.3 + 14.4 + 15.5;
	private static final double DELTA = 0.0000000001;

	@Autowired
	private GeneratorStateDao generatorStateDao;
	
	@Test
	public void getGenerationReturnsConsumptionBetweenDateTimes(){
		LocalDateTime from = DATE_2010_01_01;
		LocalDateTime to = DATE_2020_01_01;

		List<ValueSource> actual = generatorStateDao.getGeneration(from, to);

		assertEquals(2, actual.size());
		assertEquals(DATE_2010_01_01, actual.get(0).getSimulationTimeStamp());
		assertEquals(DATE_2015_01_01, actual.get(1).getSimulationTimeStamp());
		assertEquals(EXPECTED_GENERATION_2010_01_01, actual.get(0).getValue(), DELTA);
		assertEquals(EXPECTED_GENERATION_2015_01_01, actual.get(1).getValue(), DELTA);
	}
}
