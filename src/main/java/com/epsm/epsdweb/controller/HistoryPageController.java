package com.epsm.epsdweb.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epsm.epsdweb.service.chartService.ChartService;

@Controller
@RequestMapping("/app/history")
public class HistoryPageController{
	private Logger logger = LoggerFactory.getLogger(HistoryPageController.class);
	
	@Value("${model.url}")
	private String modelUrl;
	
	@Autowired
	private ChartService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String createHistoryPage(ModelMap model, HttpServletRequest request) {
	 	obtainAllNecessaryParameters(model);
	 	logger.info("Requested: page from {}.", request.getRemoteAddr());
	 	
        return "history";
    }
	
	private void obtainAllNecessaryParameters(ModelMap model){
		Map<String, String> chartsData = service.getDataForCharts();
		
		model.putAll(chartsData);
		model.put("modelUrl", modelUrl);
	}
}
