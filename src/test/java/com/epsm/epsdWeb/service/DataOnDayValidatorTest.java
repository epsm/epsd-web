package com.epsm.epsdWeb.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.epsm.epsdWeb.domain.ValueSource;
import com.epsm.epsdWeb.service.chartService.DataOnDayValidator;
import com.epsm.epsdWeb.service.chartService.ValueSourceOnDayValidator;

@RunWith(MockitoJUnitRunner.class)
public class DataOnDayValidatorTest {
	private Map<String, List<ValueSource>> dataContainer;
	
	@InjectMocks
	private DataOnDayValidator validator;
	
	@Mock
	private ValueSourceOnDayValidator sourceValidator;
	
	@Spy
	private ArrayList<String> necessaryDataValues;
	
	@Before
	public void setUp(){
		necessaryDataValues.add("value_1");
		necessaryDataValues.add("value_2");
	}
	
	@Test
	public void falseIfContainerIsNull(){
		Assert.assertFalse(validator.isDataValid(null));
	}
	
	@Test
	public void falseIfContainerKeepsNotAllSubcontainers(){
		prepareContainerWithoutAllSubcontainers();
		
		Assert.assertFalse(validator.isDataValid(dataContainer));
	}
	
	private void prepareContainerWithoutAllSubcontainers(){
		dataContainer = new HashMap<String, List<ValueSource>>();
		dataContainer.put("value_1", Collections.emptyList());
	}
	
	@Test
	public void falseIfContainerKeepsNullNecessarySubcontainers(){
		prepareContainerWithAllNullSubcontainers();
		
		Assert.assertFalse(validator.isDataValid(dataContainer));
	}
	
	private void prepareContainerWithAllNullSubcontainers(){
		dataContainer = new HashMap<String, List<ValueSource>>();
		dataContainer.put("value_1", null);
		dataContainer.put("value_2", null);
	}
	
	@Test
	public void falseIfSubcontainersKeepsNotAllData(){
		prepareContainerWithAllSubcontainers();
		prepareContainerWithoutAllDataInSubcontainers();
		
		Assert.assertFalse(validator.isDataValid(dataContainer));
	}
	
	private void prepareContainerWithAllSubcontainers(){
		dataContainer = new HashMap<String, List<ValueSource>>();
		dataContainer.put("value_1", Collections.emptyList());
		dataContainer.put("value_2", Collections.emptyList());
	}
	
	private void prepareContainerWithoutAllDataInSubcontainers(){
		when(sourceValidator.isDataValid(any())).thenReturn(false);
	}
	
	@Test
	public void trueIfDataValid(){
		prepareContainerWithAllSubcontainers();
		prepareContainerWithAllDataInSubcontainers();
		
		Assert.assertTrue(validator.isDataValid(dataContainer));
	}
	
	private void prepareContainerWithAllDataInSubcontainers(){
		when(sourceValidator.isDataValid(any())).thenReturn(true);
	}
}
