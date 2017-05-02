package com.epsm.epsdweb.service.converter;

import com.epsm.epsdweb.domain.SavedConsumerState;
import com.epsm.epsmcore.model.consumption.ConsumerState;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConsumerStateConverter extends AbstractEntityConverter<ConsumerState> {

	public List<SavedConsumerState> convert(List<ConsumerState> source) {
		return source.stream()
				.map(this::convert)
				.collect(Collectors.toList());
	}

	public SavedConsumerState convert(ConsumerState source) {
		SavedConsumerState target = new SavedConsumerState();

		fillCommonFields(target, source);
		target.setLoadInMW(source.getLoadInMW());

		return target;
	}
}
