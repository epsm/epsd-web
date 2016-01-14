package com.epsm.epsdWeb.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epsm.epsmCore.model.bothConsumptionAndGeneration.LoadCurve;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class LoadCurveJsonSerializerTest {
	private ObjectMapper mapper;
	private SimpleModule testModule;
	private LoadCurve curve;
	
	@Before
	public void setUp(){
		mapper = new ObjectMapper();
		curve = new LoadCurve(TestsConstants.LOAD_BY_HOURS);
		
		testModule = new SimpleModule();
		testModule.addSerializer(LoadCurve.class,new LoadCurveJsonSerializer());
		mapper.registerModule(testModule);
	}
	
	@Test
	public void serializesCorrect() throws JsonProcessingException{
		String expected = 
			"{\"loadByHoursInMW\":"
			+ "[64.88,59.54,55.72,51.9,48.47,48.85,48.09,57.25,76.35,91.6,100.0,99.23,"
			+ "91.6,91.6,91.22,90.83,90.83,90.83,90.83,90.83,90.83,90.83,90.83,83.96]}";

		String serialized = mapper.writeValueAsString(curve);
		Assert.assertEquals(expected, serialized);
	}
}
