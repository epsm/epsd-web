package com.epsm.epsdWeb.service.chartService;

import java.util.Comparator;

import com.epsm.epsdWeb.domain.ValueSource;

public class ValueSourceComparator implements Comparator<ValueSource>{

	@Override
	public int compare(ValueSource vs1, ValueSource vs2) {
		if(vs1.getPowerObjectTime().before(vs2.getPowerObjectTime())){
			return -1;
		}else if(vs1.getPowerObjectTime().after(vs2.getPowerObjectTime())){
			return +1;
		}else{
			return 0;
		}
	}
}
