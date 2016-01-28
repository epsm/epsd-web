package com.epsm.epsdWeb.service.chartService;

import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.epsm.epsdWeb.domain.ValueSource;
import com.epsm.epsmCore.model.generalModel.Constants;

@Component
public class ValuesConverter{
	private List<ValueSource> values;
	private StringBuilder builder;
	private String prefix = "";
	private LocalTime timeValue;
	private float floatValue;
	private DateTimeFormatter dateFormatter;
	private DecimalFormat numberFormatter;

	public ValuesConverter() {
		builder = new StringBuilder();
		dateFormatter = DateTimeFormatter.ofPattern("HH,mm");
		numberFormatter = new DecimalFormat("0.##", Constants.SYMBOLS);
	}
	
	public String convert(List<ValueSource> values){
		if(values == null){
			return "";
		}
		
		saveValues(values);
		resetState();
		sortValues();
		makeConvertation();

		return builder.toString();
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
		timeValue = source.getPowerObjectTime().toLocalTime();
		floatValue = source.getValue();
	}
	
	private String formatTime(LocalTime time){
		if(isTimeValueMax(time)){
			return "24,00";
		}else{
			return dateFormatter.format(time);
		}
	}
	
	private boolean isTimeValueMax(LocalTime time){
		return time.equals(Time.valueOf(LocalTime.MAX).toLocalTime());
	}
}
