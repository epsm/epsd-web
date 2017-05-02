package com.epsm.epsdweb.service.chartService;

import com.epsm.epsdweb.repository.ConsumerStateDao;
import com.epsm.epsdweb.repository.PowerStationStateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ChartsDataSource {

	@Autowired
	private PowerStationStateDao powerStationStateDao;

	@Autowired
	private ConsumerStateDao consumerStateDao;

	public ChartsData getData() {
		ChartsData chartsData = new ChartsData(null, null);

		return chartsData;
	}

	private Map<String, List<ValueSource>> getData(LocalDate date) {
		Map<String, List<ValueSource>> dataContainer = new HashMap<>();

//		List<ValueSource> frequencyData = frequencyDao.getFrequencies(date);
//		List<ValueSource> generationData = powerStationStateDao.getTotalGenerations(date);
//		List<ValueSource> consumptionData = consumptionDao.getTotalConsumptions(date);
//
//		dataContainer.put("frequency", frequencyData);
//		dataContainer.put("generation", generationData);
//		dataContainer.put("consumption", consumptionData);

		return dataContainer;
	}
}
