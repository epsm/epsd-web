package com.epsm.epsdWeb.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epsm.epsdWeb.domain.ValueSource;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class TotalConsumptionDaoImplTest extends AbstractDaoTest{
	
	@Autowired
	private TotalConsumptionDao dao;

	@Ignore
	@Test
	@DatabaseSetup(value="total_consumption.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testGetTotalConsumptions(){
		LocalDate date = LocalDate.of(2000, 10, 10);
		List<ValueSource> result = dao.getTotalConsumptions(date);
		
		Assert.assertEquals(2, result.size());
	}
}
