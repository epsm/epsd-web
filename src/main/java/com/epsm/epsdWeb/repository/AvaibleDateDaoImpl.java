package com.epsm.epsdWeb.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epsm.epsdWeb.domain.AvaibleDate;

@Repository
public class AvaibleDateDaoImpl implements AvaibleDateDao{
	private Logger logger = LoggerFactory.getLogger(AvaibleDateDaoImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Date> getDates(){
		List<AvaibleDate> objects = getObjects();
		List<Date> dates = convertToDates(objects);
		
		logger.debug("Invoked: getEntry(), returnded {}.", dates);
		
		return dates; 
	}
	
	@SuppressWarnings("unchecked")
	private List<AvaibleDate> getObjects(){
		Query query = em.createQuery("SELECT e FROM AvaibleDate e");
		
		return query.getResultList();
	}
	
	private List<Date> convertToDates(List<AvaibleDate> objects){
		List<Date> dates = new ArrayList<Date>();
		
		for(AvaibleDate object: objects){
			dates.add(object.getDate());
		}
		
		return dates;
	}
}
