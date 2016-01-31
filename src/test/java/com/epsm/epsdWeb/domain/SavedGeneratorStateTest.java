package com.epsm.epsdWeb.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SavedGeneratorStateTest {
	private SavedGeneratorState state;
	
	@Before
	public void setUp(){
		state = new SavedGeneratorState();
		state.setPowerObjectId(888);
		state.setGeneratorNumber(777);
	}
	
	@Test
	public void toStringTest(){
		String expected = "SavedGeneratorState: power station#888, generator number#777";
		String actual = state.toString();
		
		Assert.assertEquals(expected, actual);
	}
}
