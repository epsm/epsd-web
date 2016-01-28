package com.epsm.epsdWeb.domain;

import java.sql.Time;

public interface ValueSource {
	float getValue();
	Time getPowerObjectTime();
}
