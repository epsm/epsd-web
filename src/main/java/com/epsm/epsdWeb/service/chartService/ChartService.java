package com.epsm.epsdWeb.service.chartService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChartService {
	private ChartsData data;
	private Map<String, String> charts;
	private LocalDate chartsDate;
	private Logger logger;

	@Autowired
	private ChartsDataSource dataSource;
	
	@Autowired
	private ValuesConverter converter;
	
	public ChartService(){
		charts = new HashMap<String, String>();
		chartsDate = LocalDate.MIN;
		logger = LoggerFactory.getLogger(ChartService.class);
		
		charts.put("date", "no data");
	}
	
	public Map<String, String> getDataForCharts(){
		getData();
		
		if(data == null){
			return charts;
		}
		
		if(isChartsDataOutdated()){
			refreshCharts();
		}
		
		logger.debug("Invoked: getDataForCharts(), returned {}.", charts);
		
		return charts;
	}
	
	private void getData(){
		data = dataSource.getData();
	}
	
	private boolean isChartsDataOutdated(){
		return chartsDate.isBefore(data.getDate());
	}
	
	private void refreshCharts(){
		clearCharts();
		createNewCharts();
		refreshChartDate();
	}
	
	private void clearCharts(){
		charts.clear();
	}
	
	private void createNewCharts(){
		String frequency = converter.convert(data.getData("frequency"));
		String generation = converter.convert(data.getData("generation"));
		String consumption = converter.convert(data.getData("consumption"));
		charts.put("frequencyChartData", frequency);
		charts.put("generationChartData", generation);
		charts.put("consumptionChartData", consumption);
		charts.put("date", data.getDate().toString());
	}
	
	private void refreshChartDate(){
		chartsDate = data.getDate();
	}
}
