package com.epsm.epsdWeb.service.chartDataService;

import java.sql.Time;

public interface ValueSource {
	float getValue();
	Time getPowerObjectTime();
}
