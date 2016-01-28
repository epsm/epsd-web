package com.epsm.epsdWeb.service.chartService;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import com.epsm.epsdWeb.repository.AvaibleDateDao;

@RunWith(MockitoJUnitRunner.class)
public class AvaibleDateSourceTest {
	
	@InjectMocks
	private AvaibleDateSource avaibleDataSource;
	
	@Mock
	private AvaibleDateDao dateDao;
	
	@Mock
	private DataSource dataSource;
	
	@Mock
	private DataOnDayValidator validator;
	
	@Test
	public void getLastAvaibleDateReturnsLocalDateMinIfdateDaoReturnsNull(){
		when(dateDao.getDates()).thenReturn(null);
		
		Assert.assertEquals(LocalDate.MIN, avaibleDataSource.getLastAvaibleDate());
	}
	
	@Test
	public void getLastAvaibleDateReturnsLocalDateMinIfdateDaoReturnsEmptyListAnd(){
		when(dateDao.getDates()).thenReturn(null);
		
		Assert.assertEquals(LocalDate.MIN, avaibleDataSource.getLastAvaibleDate());
	}
}
