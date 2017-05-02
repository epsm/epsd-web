package com.epsm.epsdweb.service;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.epsm.epsdweb.domain.SavedPowerStationState;
import com.epsm.epsmcore.model.consumption.ConsumerType;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epsm.epsdweb.repository.ConsumerStateDao;
import com.epsm.epsdweb.repository.PowerStationStateDao;
import com.epsm.epsdweb.service.converter.ConsumerStateConverter;
import com.epsm.epsdweb.service.converter.PowerStationStateConverter;
import com.epsm.epsmcore.model.consumption.ConsumerState;

@RunWith(MockitoJUnitRunner.class)
public class PowerObjectServiceImplTest {

	private List<com.epsm.epsmcore.model.generation.PowerStationState> powerStationStates;
	private List<ConsumerState> consumerStates;
	
	@InjectMocks
	private PowerObjectServiceImpl service;
	
	@Mock
	private PowerStationStateDao generatorDao;
	
	@Mock
	private ConsumerStateDao consumerDao;
	
	@Mock
	private ConsumerStateConverter consumerConverter;
	
	@Mock
	private PowerStationStateConverter powerStationConverter;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Before
	public void setUp(){
		List<SavedPowerStationState> convertedStates = new ArrayList<SavedPowerStationState>();
		SavedPowerStationState state = new SavedPowerStationState();
		convertedStates.add(state);
		when(powerStationConverter.convert(any())).thenReturn(convertedStates);
	}

	@Test
	public void savesPowerStationState(){
		powerStationStates = asList(new com.epsm.epsmcore.model.generation.PowerStationState(1, LocalDateTime.MIN,  1));
		
		service.savePowerStationStates(powerStationStates);
		
		verify(generatorDao).saveStates(any());
	}
	
	@Test
	public void usesPowerStationStateConverterToConvertPowerStationState(){
		powerStationStates = asList(new com.epsm.epsmcore.model.generation.PowerStationState(1, LocalDateTime.MIN,  1));
		
		service.savePowerStationStates(powerStationStates);
		
		verify(powerStationConverter).convert(powerStationStates);
	}
	
	@Test
	public void savesConsumerState(){
		consumerStates = asList(new ConsumerState(1, LocalDateTime.MIN, 1, ConsumerType.RANDOM_LOAD));
		
		service.saveConsumerStates(consumerStates);
		
		verify(consumerDao).saveStates(any());
	}
	
	@Test
	public void usesConsumerStateConverterToConvertConsumerState(){
		consumerStates = asList(new ConsumerState(1, LocalDateTime.MIN, 1, ConsumerType.RANDOM_LOAD));
		
		service.saveConsumerStates(consumerStates);
		
		verify(consumerConverter).convert(consumerStates);
	}
}
