package com.epsm.epsdWeb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epsm.epsdWeb.service.chartDataService.ChartService;

@Controller
@RequestMapping("/history")
public class HistoryPageController{
	
	@Value("${model.url}")
	private String modelUrl;
	
	@Autowired
	private ChartService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String createHistoryPage(ModelMap model) {
	 	obtainAllNecessaryParameters(model);
	 	
        return "history";
    }
	
	private void obtainAllNecessaryParameters(ModelMap model){
		Map<String, String> chartsData = service.getDataForCharts();
		
		model.put("frequencyChartData", chartsData.get("frequencyChartData"));
		model.put("date", chartsData.get("date"));
		model.put("modelUrl", modelUrl);
	}
}
