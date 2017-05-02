package com.epsm.epsdweb.service.chartService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Component
public class ValuesConverter {

	private List<ValueSource> values;
	private StringBuilder builder;
	private String prefix = "";
	private LocalTime timeValue;
	private float floatValue;
	private DateTimeFormatter dateFormatter;
	private DecimalFormat numberFormatter;
	private String result;
	private Logger logger = LoggerFactory.getLogger(ValuesConverter.class);

	public ValuesConverter() {
		builder = new StringBuilder();
		dateFormatter = DateTimeFormatter.ofPattern("HH,mm");
		numberFormatter = new DecimalFormat("0.##", new DecimalFormatSymbols(Locale.US));
	}
	
	public String convert(List<ValueSource> values){
		if(values == null){
			logger.debug("Converted: got null instead List<ValueSource>.");
			return "";
		}
		
		saveValues(values);
		resetState();
		sortValues();
		makeConvertation();
		getResult();
		logger.debug("Converted: {} values", values.size());

		return result;
	}
	
	private void saveValues(List<ValueSource> values){
		this.values = values;
	}
	
	private void resetState(){
		builder.setLength(0);
		prefix = "";
	}
	
	private void sortValues(){
		Collections.sort(values, new ValueSourceComparator());
	}
	
	private void makeConvertation(){
		builder.append("[");
		
		for(ValueSource source: values){
			getFieldsFromSource(source);
			
			builder.append(prefix);
			builder.append("[[");
			builder.append(formatTime(timeValue));
			builder.append("], ");
			builder.append(numberFormatter.format(floatValue));
			builder.append("]");
			prefix = ",";
		}
		
		builder.append("]");
	}
	
	private void getFieldsFromSource(ValueSource source){
		timeValue = source.getSimulationTimeStamp().toLocalTime();
		floatValue = source.getValue();
	}
	
	private String formatTime(LocalTime time){
		if(time.isAfter(LocalTime.of(23, 59, 0))){
			return "24,00";
		}else{
			return dateFormatter.format(time);
		}
	}
	
	private void getResult(){
		result = builder.toString();
	}
}
