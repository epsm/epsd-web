package com.epsm.epsdWeb.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HttpURLConnectionSource {
	private Logger logger = LoggerFactory.getLogger(HttpURLConnectionSource.class);
	
	public HttpURLConnection getConnection(String url){
		URL urlObject = constructUrl(url);
		
		if(urlObject != null){
			logger.debug("HttpURLConnection created for {}.");
			
			return constructConnection(urlObject);
		}else{
			return null;
		}
	}
	
	private URL constructUrl(String url){
		try{
			return new URL(url);
		} catch (MalformedURLException e) {
			logger.warn("Error creating Url {}.", e);
			
			return null;
		}
	}
	
	private HttpURLConnection constructConnection(URL url){
		try{
			return (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			logger.warn("Error creating Url {}.", e);
			
			return null;
		}
	}
}
