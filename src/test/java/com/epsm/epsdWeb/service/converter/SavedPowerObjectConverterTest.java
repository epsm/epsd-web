package com.epsm.epsdWeb.service.converter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epsm.epsdWeb.domain.SavedPowerObject;
import com.epsm.epsmCore.model.dispatch.State;

public class SavedPowerObjectConverterTest {
	private SavedPowerObjectConverter<State> converter;
	private State source;
	private SavedPowerObject convertedObject;
	private final long OBJECT_ID = 457;
	private final LocalDateTime REAL_TIME_STAMP = LocalDateTime.of(1, 2, 3, 4, 5, 6, 7);
	private final LocalDateTime OBJECT_TIME_STAMP = LocalDateTime.of(1, 2, 3, 4, 5, 6, 7);

	@Before
	public void setUp(){
		converter = new SavedPowerObjectConverter<State>(){};
		source = mock(State.class);
		convertedObject = new SavedPowerObject(){};
		
		when(source.getPowerObjectId()).thenReturn(OBJECT_ID);
		when(source.getRealTimeStamp()).thenReturn(REAL_TIME_STAMP);
		when(source.getSimulationTimeStamp()).thenReturn(OBJECT_TIME_STAMP);
	
		makeConvertation();
	}
	
	private void makeConvertation(){
		converter.saveSource(source);
		converter.getGeneralFields();
		converter.fillGeneralFields(convertedObject);
	}
	
	@Test
	public void powerObjectInConvertedInstanceIsCorrect(){
		Assert.assertEquals(OBJECT_ID, convertedObject.getPowerObjectId());
	}
	
	@Test
	public void realTimeStampInConvertedInstanceIsCorrect(){
		Assert.assertEquals(Timestamp.valueOf(REAL_TIME_STAMP), convertedObject.getRealTimeStamp());
	}
	
	@Test
	public void powerObjectTimeInConvertedInstanceIsCorrect(){
		Assert.assertEquals(Time.valueOf(OBJECT_TIME_STAMP.toLocalTime()),
				convertedObject.getPowerObjectTime());
	}
	
	@Test
	public void powerObjectDateInConvertedInstanceIsCorrect(){
		Assert.assertEquals(Date.valueOf(OBJECT_TIME_STAMP.toLocalDate()),
				convertedObject.getPowerObjectDate());
	}
}
