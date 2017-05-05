package com.epsm.epsdweb.repository;

import com.epsm.epsdweb.service.chartService.ValueSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConsumerStateDaoTest extends AbstractDaoTest {
	
	@Autowired
	private ConsumerStateDao dao;
	
	@Test
	public void getConsumtionReturnsConsumptionBetweenDateTimes(){
		LocalDateTime from = LocalDateTime.of(2010, 1, 1, 0, 0);
		LocalDateTime to = LocalDateTime.of(2020, 1, 1, 0, 0);

		List<ValueSource> actual = dao.getConsumption(from, to);

		assertEquals(2, actual.size());
		assertEquals(LocalDateTime.of(2010, 1, 1, 0, 0), actual.get(0).getSimulationTimeStamp());
		assertEquals(20.2, actual.get(0).getValue(), 0);
		assertEquals(10, actual.get(1).getValue(), 0);
	}
}
