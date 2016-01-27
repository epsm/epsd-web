package com.epsm.epsdWeb.service.chartDataService;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

import com.epsm.epsmCore.model.generalModel.Constants;

public class ChartData{
	private Map<LocalTime, Float> data;
	private StringBuilder builder;
	private String stringValue;
	private String prefix = "";
	private DateTimeFormatter dateFormatter;
	private DecimalFormat numberFormatter;

	public ChartData(Map<LocalTime, Float> data) {
		if(data != null){
			this.data = new TreeMap<LocalTime, Float>(data);
		}
		
		builder = new StringBuilder();
		dateFormatter = DateTimeFormatter.ofPattern("HH,mm");
		numberFormatter = new DecimalFormat("0.##", Constants.SYMBOLS);
	}
	
	public int size(){
		return data == null ? 0 : data.size(); 
	}
	
	public String toString(){
		if(data == null){
			return "";
		}else if(stringValue == null){
			return prepareString();
		}else{
			return stringValue;
		}
	}
	
	private String prepareString(){
		builder.append("[");
		
		data.forEach((k,v)->{
			builder.append(prefix);
			builder.append("[[");
			
			if(k.equals(LocalTime.MAX)){
				builder.append("24,00");
			}else{
				builder.append(dateFormatter.format(k));
			}
			
			builder.append("], ");
			builder.append(numberFormatter.format(v));
			builder.append("]");
			prefix = ",";
		});
		
		builder.append("]");
		stringValue = builder.toString();
		
		return stringValue;
	}
}
