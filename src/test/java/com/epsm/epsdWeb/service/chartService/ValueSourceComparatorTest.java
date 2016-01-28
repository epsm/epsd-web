package com.epsm.epsdWeb.service.chartService;

import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.sql.Time;
import java.time.LocalTime;

import com.epsm.epsdWeb.domain.ValueSource;

public class ValueSourceComparatorTest {
	private ValueSourceComparator comparator = new ValueSourceComparator();
	private ValueSource vs1 = mock(ValueSource.class);
	private ValueSource vs2 = mock(ValueSource.class);
	
	@Test
	public void minusOneIfVs1TimeLessThanVs2Time(){
		when(vs1.getPowerObjectTime()).thenReturn(Time.valueOf(LocalTime.MIN));
		when(vs2.getPowerObjectTime()).thenReturn(Time.valueOf(LocalTime.MAX));
		
		Assert.assertEquals(-1, comparator.compare(vs1, vs2));
	}
	
	@Test
	public void oneIfVs1TimeMoreThanVs2Time(){
		when(vs1.getPowerObjectTime()).thenReturn(Time.valueOf(LocalTime.MAX));
		when(vs2.getPowerObjectTime()).thenReturn(Time.valueOf(LocalTime.MIN));
		
		Assert.assertEquals(1, comparator.compare(vs1, vs2));
	}
	
	@Test
	public void zeroIfVs1TimeEqualsVs2Time(){
		when(vs1.getPowerObjectTime()).thenReturn(Time.valueOf(LocalTime.MAX));
		when(vs2.getPowerObjectTime()).thenReturn(Time.valueOf(LocalTime.MAX));
		
		Assert.assertEquals(0, comparator.compare(vs1, vs2));
	}
}
