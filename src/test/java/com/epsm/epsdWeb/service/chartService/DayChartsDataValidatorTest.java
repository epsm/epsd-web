package com.epsm.epsdWeb.service.chartService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epsm.epsdWeb.domain.ValueSource;

@RunWith(MockitoJUnitRunner.class)
public class DayChartsDataValidatorTest {
	private Map<String, List<ValueSource>> dataContainer;
	
	@InjectMocks
	private DayChartsDataValidator validator;
	
	@Mock
	private DayChartDataValidator sourceValidator;
	
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
		dataContainer.put("frequency", Collections.emptyList());
	}
	
	@Test
	public void falseIfContainerKeepsNullNecessarySubcontainers(){
		prepareContainerWithAllNullSubcontainers();
		
		Assert.assertFalse(validator.isDataValid(dataContainer));
	}
	
	private void prepareContainerWithAllNullSubcontainers(){
		dataContainer = new HashMap<String, List<ValueSource>>();
		dataContainer.put("frequency", null);
		dataContainer.put("generation", null);
		dataContainer.put("consumption", null);
	}
	
	@Test
	public void falseIfSubcontainersKeepsNotAllData(){
		prepareContainerWithAllSubcontainers();
		prepareContainerWithoutAllDataInSubcontainers();
		
		Assert.assertFalse(validator.isDataValid(dataContainer));
	}
	
	private void prepareContainerWithAllSubcontainers(){
		dataContainer = new HashMap<String, List<ValueSource>>();
		dataContainer.put("frequency", Collections.emptyList());
		dataContainer.put("generation", Collections.emptyList());
		dataContainer.put("consumption", Collections.emptyList());
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
