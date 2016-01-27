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

import com.epsm.epsdWeb.domain.SavedGeneratorState;

@Repository
public class SavedGeneratorStateDaoImpl implements SavedGeneratorStateDao{
	private Logger logger = LoggerFactory.getLogger(SavedGeneratorStateDaoImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public LocalDate getLastEntryDate(){
		Date result = null;
		Query query = em.createQuery("SELECT MAX(e.powerObjectDate) FROM SavedGeneratorState e");
		
		result = (Date) query.getSingleResult();
		logger.debug("Requested: last saved date, returned {}.", result);
		
		if(result == null){
			return LocalDate.MIN;
		}else{
			return result.toLocalDate();
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<SavedGeneratorState> getStatesOnDate(LocalDate date){
		List<SavedGeneratorState> result = null;
		Date dateToSearsch = Date.valueOf(date);
		Query query = em.createQuery(
				"SELECT e FROM SavedGeneratorState e"
				+ " WHERE e.powerObjectDate = :dateToSearsch");
		
		query.setParameter("dateToSearsch", dateToSearsch);
		result = query.getResultList();
		logger.debug("Requested: List<SavedGeneratorState> for {}, returned {}.",
				date, result);
	
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Long> getPowerObjectsIdsOnDate(LocalDate date){
		List<Long> result = null;
		Date dateToSearsch = Date.valueOf(date);
		Query query = em.createQuery(
				"SELECT DISTINCT e.powerObjectId FROM SavedGeneratorState e"
				+ " WHERE e.powerObjectDate = :dateToSearsch");
		
		query.setParameter("dateToSearsch", dateToSearsch);
		result = query.getResultList();
		logger.debug("Requested: power object Ids on {}, returned {}.", date, result);
		
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Integer> getGeneratorsNumbersOnDateForPowerStation(
			LocalDate date, long powerObjectId){
		
		List<Integer> result = null;
		Date dateToSearsch = Date.valueOf(date);
		Query query = em.createQuery(
				"SELECT DISTINCT e.generatorNumber FROM SavedGeneratorState e"
				+ " WHERE e.powerObjectDate = :dateToSearsch"
				+ " AND e.powerObjectId = :powerObjectId");
		
		query.setParameter("dateToSearsch", dateToSearsch);
		query.setParameter("powerObjectId", powerObjectId);
		result = query.getResultList();
		logger.debug("Requested: generator numbers for {} and power station, returned {}.",
				date, result);
		
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<SavedGeneratorState> getStatesOnDateForPowerStationAndGenerator(
			LocalDate date, long powerObjectId, int generatorNumber){
		
		List<SavedGeneratorState> result = null;
		Date dateToSearsch = Date.valueOf(date);
		Query query = em.createQuery(
				"SELECT DISTINCT e FROM SavedGeneratorState e"
				+ " WHERE e.powerObjectDate = :dateToSearsch"
				+ " AND e.powerObjectId = :powerObjectId"
				+ " AND e.generatorNumber = :generatorNumber");
		
		query.setParameter("dateToSearsch", dateToSearsch);
		query.setParameter("powerObjectId", powerObjectId);
		query.setParameter("generatorNumber", generatorNumber);
		result = query.getResultList();
		logger.debug("Requested: generator states for {} power station and generator,"
				+ " returned {}.",date, result);
		
		return result;
	}
	
	@Override
	public Float getMidnightFrequencyOnDateForPowerStationAndGenerator(
			LocalDate date, long powerObjectId, int generatorNumber){
		
		Float result = null;
		Date dateToSearsch = Date.valueOf(date);
		Time time = Time.valueOf(LocalTime.MIDNIGHT);
		
		Query query = em.createQuery(
				"SELECT e.frequency FROM SavedGeneratorState e"
				+ " WHERE e.powerObjectDate = :dateToSearsch"
				+ " AND e.powerObjectTime = :time"
				+ " AND e.powerObjectId = :powerObjectId"
				+ " AND e.generatorNumber = :generatorNumber");
		
		query.setParameter("dateToSearsch", dateToSearsch);
		query.setParameter("time", time);
		query.setParameter("powerObjectId", powerObjectId);
		query.setParameter("generatorNumber", generatorNumber);
		
		try{
			result = (Float) query.getSingleResult();
			logger.debug("Requested: midnight frequency for {} power station and generator,"
					+ " returned {}.",date, result);			
			return result;
		}catch(NoResultException e){
			logger.debug("Requested: midnight frequency for {} power station and generator,"
				+ " returned {}.",date, Float.NEGATIVE_INFINITY);
			return Float.NEGATIVE_INFINITY;
		}
	}
	
	@Override
	public void saveState(SavedGeneratorState state) {
		em.persist(state);
		logger.debug("Saved: {}.", state);
	}
}
