package com.epsm.epsdweb.service.chartService;

import com.epsm.epsdweb.domain.SavedPowerStationState;
import com.epsm.epsdweb.repository.ConsumerStateDao;
import com.epsm.epsdweb.repository.DateTimeDao;
import com.epsm.epsdweb.repository.PowerStationStateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartService {

	@Autowired
	private PowerStationStateDao powerStationStateDao;

	@Autowired
	private ConsumerStateDao consumerStateDao;

	@Autowired
	private DateTimeDao dateTimeDao;

	@Autowired
	private ValuesConverter converter;

	public ChartsData getChartData() {
		LocalDateTime to = dateTimeDao.getLastDate();
		LocalDateTime from = to.withHour(0).withMinute(0).withSecond(0).withNano(0);

		List<SavedPowerStationState> stationStates = powerStationStateDao.getStates(from, to);

		String frequencyData = converter.convert(getFrequency(stationStates));
		String generationData = converter.convert(getGenereation(stationStates));
		String consumptionData = converter.convert(consumerStateDao.getStates(from, to));

		return new ChartsData(to.toLocalDate(), frequencyData, generationData, consumptionData);
	}

	private List<ValueSource> getFrequency(List<SavedPowerStationState> source) {
		return source.stream()
				.map(s -> new ValueSource(s.getFrequency(), s.getSimulationTimeStamp()))
				.collect(Collectors.toList());
	}

	private List<ValueSource> getGenereation(List<SavedPowerStationState> source) {
		return source.stream()
				.map(s -> new ValueSource(s.getGenerationInMW(), s.getSimulationTimeStamp()))
				.collect(Collectors.toList());
	}
}
