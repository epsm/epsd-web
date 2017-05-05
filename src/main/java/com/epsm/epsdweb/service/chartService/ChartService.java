package com.epsm.epsdweb.service.chartService;

import com.epsm.epsdweb.repository.ConsumerStateDao;
import com.epsm.epsdweb.repository.DateTimeDao;
import com.epsm.epsdweb.repository.GeneratorStateDao;
import com.epsm.epsdweb.repository.PowerStationStateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChartService {

	@Autowired
	private PowerStationStateDao powerStationStateDao;

	@Autowired
	private GeneratorStateDao generatorStateDao;

	@Autowired
	private ConsumerStateDao consumerStateDao;

	@Autowired
	private DateTimeDao dateTimeDao;

	@Autowired
	private ValuesConverter converter;

	public ChartsData getChartData() {
		LocalDateTime to = dateTimeDao.getLastDate();
		LocalDateTime from = to.withHour(0).withMinute(0).withSecond(0).withNano(0);

		String frequencyData = converter.convert(powerStationStateDao.getFrequency(from, to));
		String generationData = converter.convert(generatorStateDao.getGeneration(from, to));
		String consumptionData = converter.convert(consumerStateDao.getConsumption(from, to));

		return new ChartsData(to.toLocalDate(), frequencyData, generationData, consumptionData);
	}
}
