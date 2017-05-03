package com.epsm.epsdweb.repository;

import com.epsm.epsdweb.domain.SavedPowerStationState;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PowerStationStateDaoTest extends AbstractDaoTest {
	
	@Autowired
	private PowerStationStateDao dao;
	
	@Test
	public void getStatesReturnsStatesBetweenDateTimes(){
		LocalDateTime from = LocalDateTime.of(2010, 1, 1, 0, 0);
		LocalDateTime to = LocalDateTime.of(2020, 1, 1, 0, 0);

		List<SavedPowerStationState> actual = dao.getStates(from, to);

		assertEquals(2, actual.size());
		assertEquals(LocalDateTime.of(2010, 1, 1, 0, 0), actual.get(0).getSimulationTimeStamp());
	}
}
