package com.epsm.epsdweb.service.chartService;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;

public class ValuesConverterTest {

	private ValuesConverter converter = new ValuesConverter();

	@Test
	public void convertReturnsExpectedData() {
		List<ValueSource> source = asList(
				new ValueSource(50.578, LocalDateTime.of(2000, 10, 10, 20, 10, 0)),
				new ValueSource(49.578, LocalDateTime.of(2000, 10, 10, 10, 20, 0)),
				new ValueSource(50.0, LocalDateTime.of(2000, 10, 10, 23, 59, 1))
		);

		String expected = "[[[20,10], 50.58],[[10,20], 49.58],[[24,00], 50]]";

		Assert.assertEquals(expected, converter.convert(source));
	}
}