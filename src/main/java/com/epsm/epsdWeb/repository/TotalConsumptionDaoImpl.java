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

import com.epsm.epsdWeb.domain.TotalConsumption;
import com.epsm.epsdWeb.domain.ValueSource;

@Repository
public class TotalConsumptionDaoImpl implements TotalConsumptionDao{
	private Logger logger = LoggerFactory.getLogger(TotalConsumptionDaoImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<ValueSource> getTotalConsumptions(LocalDate date){
		List<ValueSource> result = getDataOnDate(date);
	
		try{
			TotalConsumption nextMidnightValue = getDataOnNextMidnight(date);
			setNextMidnightValueAsLastValueOnThisDay(result, nextMidnightValue);
			logger.debug("Requested: getTotalConsumption(...) on {}.", date);
			
			return result;
		}catch(NoResultException ex){
			logger.debug("Requested: getTotalConsumption(...) on {}, nextMidnightValue = null.", date);
			return result;
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<ValueSource> getDataOnDate(LocalDate date){
		Query query = em.createQuery(
				"SELECT e FROM TotalConsumption e WHERE e.powerObjectDate = :dateToSearsch");
		
		query.setParameter("dateToSearsch", Date.valueOf(date));
		
		return query.getResultList();
	}

	private TotalConsumption getDataOnNextMidnight(LocalDate date){
		Query query = em.createQuery(
				"SELECT e FROM TotalConsumption e WHERE e.powerObjectDate = :date AND e.powerObjectTime = :time");
		
		query.setParameter("date", Date.valueOf(date.plusDays(1)));
		query.setParameter("time", Time.valueOf(LocalTime.MIDNIGHT));
		
		return (TotalConsumption) query.getSingleResult();
	}
	
	private void setNextMidnightValueAsLastValueOnThisDay(List<ValueSource> values,
			TotalConsumption nextMidnightValue){
		
		nextMidnightValue.setPowerObjectTime(Time.valueOf(LocalTime.MAX));
		values.add(nextMidnightValue);
	}
}
