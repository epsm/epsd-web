package com.epsm.epsdweb.service.chartService;

import java.util.Comparator;

public class ValueSourceComparator implements Comparator<ValueSource>{

	@Override
	public int compare(ValueSource vs1, ValueSource vs2) {
		return vs1.getSimulationTimeStamp().compareTo(vs2.getSimulationTimeStamp());
	}
}
