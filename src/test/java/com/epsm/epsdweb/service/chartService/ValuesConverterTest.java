package com.epsm.epsdweb.service.chartService;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValuesConverterTest {
	private ValuesConverter converter = new ValuesConverter();
	private String result;
	private List<ValueSource> values;

	@Test
	public void toStringMethodReturnsEmptyStringIfDataIsNull() {
		result = converter.convert(null);

		Assert.assertEquals("", result);
	}

	@Test
	public void returnsExpectedValueFirstTime() {
		String expected = "[[[10,20], 49.58],[[20,10], 50.58],[[24,00], 50]]";
		prepareFilledData();

		result = converter.convert(values);

		Assert.assertEquals(expected, result);
	}

	private void prepareFilledData() {
		values = new ArrayList<ValueSource>();
		ValueSource vs1 = mock(ValueSource.class);
		ValueSource vs2 = mock(ValueSource.class);
		ValueSource vs3 = mock(ValueSource.class);
		when(vs1.getSimulationTimeStamp()).thenReturn(LocalDateTime.of(2000, 10, 10, 20, 10, 00));
		when(vs2.getSimulationTimeStamp()).thenReturn(LocalDateTime.of(2000, 10, 10, 10, 20, 00));
		when(vs3.getSimulationTimeStamp()).thenReturn(LocalDateTime.of(2000, 10, 10, 23, 59, 01));
		when(vs1.getValue()).thenReturn(50.578F);
		when(vs2.getValue()).thenReturn(49.578F);
		when(vs3.getValue()).thenReturn(50F);
		values.add(vs1);
		values.add(vs3);
		values.add(vs2);
	}

	@Test
	public void toStringMethodReturnsExpectedValueForNextTimes() {
		String expected = "[[[10,20], 49.58],[[20,10], 50.58],[[24,00], 50]]";
		prepareFilledData();

		result = converter.convert(values);
		result = converter.convert(values);

		Assert.assertEquals(expected, result);
	}
}