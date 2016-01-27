package com.epsm.epsdWeb.repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epsm.epsdWeb.domain.TotalGeneration;

@Repository
public class TotalGenerationDaoImpl implements TotalGenerationDao{
	private Logger logger = LoggerFactory.getLogger(TotalGenerationDaoImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<TotalGeneration> getTotalGenerations(LocalDate date){
		List<TotalGeneration> result = getDataOnDate(date);
	
		try{
			TotalGeneration nextMidnightValue = getDataOnNextMidnight(date);
			setNextMidnightValueAsLastValueOnThisDay(result, nextMidnightValue);
			logger.debug("Requested: getTotalGenerations(...) on {}.", date);
			
			return result;
		}catch(NoResultException ex){
			logger.debug("Requested: getTotalGenerations(...) on {}, nextMidnightValue = null.", date);
			return result;
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<TotalGeneration> getDataOnDate(LocalDate date){
		Query query = em.createQuery(
				"SELECT e FROM TotalGeneration e WHERE e.powerObjectDate = :dateToSearsch");
		
		query.setParameter("dateToSearsch", Date.valueOf(date));
		
		return query.getResultList();
	}

	private TotalGeneration getDataOnNextMidnight(LocalDate date){
		Query query = em.createQuery(
				"SELECT e FROM TotalGeneration e WHERE e.powerObjectDate = :date AND e.powerObjectTime = :time");
		
		query.setParameter("date", Date.valueOf(date.plusDays(1)));
		query.setParameter("time", Time.valueOf(LocalTime.MIDNIGHT));
		
		return (TotalGeneration) query.getSingleResult();
	}
	
	private void setNextMidnightValueAsLastValueOnThisDay(List<TotalGeneration> values,
			TotalGeneration nextMidnightValue){
		
		nextMidnightValue.setPowerObjectTime(Time.valueOf(LocalTime.MAX));
		values.add(nextMidnightValue);
	}
}
