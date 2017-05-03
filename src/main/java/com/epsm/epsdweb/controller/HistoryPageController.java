package com.epsm.epsdweb.controller;

import com.epsm.epsdweb.service.chartService.ChartsData;
import com.epsm.epsdweb.service.chartService.ChartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/app/history")
public class HistoryPageController{
	private Logger logger = LoggerFactory.getLogger(HistoryPageController.class);
	
	@Value("${model.url}")
	private String modelUrl;
	
	@Autowired
	ChartService chartService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getHistoryPage(ModelMap model, HttpServletRequest request) {
	 	fillModel(model);
	 	logger.info("Requested: page from {}.", request.getRemoteAddr());
	 	
        return "history";
    }
	
	private void fillModel(ModelMap model){
		ChartsData chartsData = chartService.getChartData();

		model.put("date", chartsData.getOnDate());
		model.put("consumption", chartsData.getConsumptionInMW());
		model.put("generation", chartsData.getGenerationInMW());
		model.put("frequency", chartsData.getFrequency());
		model.put("modelUrl", modelUrl);
	}
}
