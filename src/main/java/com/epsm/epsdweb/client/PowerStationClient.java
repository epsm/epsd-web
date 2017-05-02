package com.epsm.epsdweb.client;

import com.epsm.epsmcore.model.generation.PowerStationGenerationSchedule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PowerStationClient extends AbstractClient<PowerStationGenerationSchedule>{

	@Value("${api.powerstation.command}")
	private String url;
	
	@Override
	protected String getUrl() {
		return url;
	}
}
