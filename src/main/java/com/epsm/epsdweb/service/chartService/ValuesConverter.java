package com.epsm.epsdweb.service.chartService;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Component
public class ValuesConverter {

	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH,mm");
	private DecimalFormat numberFormatter = new DecimalFormat("0.##", new DecimalFormatSymbols(Locale.US));

	public String convert(List<ValueSource> values) {
		StringBuilder builder = new StringBuilder();
		String prefix = "";

		builder.append("[");

		for (ValueSource value : values) {

			builder.append(prefix);
			builder.append("[[");
			builder.append(formatTime(value.getSimulationTimeStamp()));
			builder.append("], ");
			builder.append(numberFormatter.format(value.getValue()));
			builder.append("]");
			prefix = ",";
		}

		builder.append("]");

		return builder.toString();
	}

	private String formatTime(LocalDateTime localDateTime) {
		if (localDateTime.toLocalTime().isAfter(LocalTime.of(23, 59, 0))) {
			return "24,00";
		} else {
			return dateFormatter.format(localDateTime);
		}
	}
}
