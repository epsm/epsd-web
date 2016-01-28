package com.epsm.epsdWeb.service.chartService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.epsm.epsdWeb.domain.ValueSource;

@Component
public class DataSource {
	public Map<String, List<ValueSource>> getData(LocalDate date){
		return null;
	}
}
