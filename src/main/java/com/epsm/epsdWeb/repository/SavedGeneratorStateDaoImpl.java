package com.epsm.epsdWeb.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
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
	public Date getLastSavedDate(){
		Date result = null;
		Query query = em.createQuery("SELECT MAX(e.powerObjectDate) FROM SavedGeneratorState e");
		
		result = (Date) query.getSingleResult();
		logger.debug("Requested: last saved date, returnde {}.", result);
		
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<SavedGeneratorState> getStatesOnDate(LocalDate date){
		List<SavedGeneratorState> result = null;
		Date dateToSearsch = Date.valueOf(date);
		Query query = em.createQuery("SELECT e FROM SavedGeneratorState e WHERE e.powerObjectDate"
				+ " = :dateToSearsch");
		
		query.setParameter("dateToSearsch", dateToSearsch);
		result = query.getResultList();
		logger.debug("Requested: List<SavedGeneratorState> for date {}, returned {}.",
				date, result);
	
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Long> getPowerObjectsIdsOnDate(LocalDate date){
		List<Long> result = null;
		Date dateToSearsch = Date.valueOf(date);
		Query query = em.createQuery("SELECT DISTINCT e.powerObjectId FROM SavedGeneratorState e"
				+ " WHERE e.powerObjectDate = :dateToSearsch");
		
		query.setParameter("dateToSearsch", dateToSearsch);
		result = query.getResultList();
		logger.debug("Requested: power object Ids on date, returned {}.", result);
		
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Integer> getGeneratorsNumbersOnDateForPowerStation(
			LocalDate date, long powerObjectId){
		
		List<Integer> result = null;
		Date dateToSearsch = Date.valueOf(date);
		Query query = em.createQuery("SELECT DISTINCT e.generatorNumber FROM SavedGeneratorState e"
				+ " WHERE e.powerObjectDate = :dateToSearsch AND e.powerObjectId = :powerObjectId");
		
		query.setParameter("dateToSearsch", dateToSearsch);
		query.setParameter("powerObjectId", powerObjectId);
		result = query.getResultList();
		logger.debug("Requested: generator numbers for power station, returned {}.", result);
		
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<SavedGeneratorState> getGeneratorStatesOnDateForPowerStation(
			LocalDate date, long powerObjectId){
		
		List<SavedGeneratorState> result = null;
		Date dateToSearsch = Date.valueOf(date);
		Query query = em.createQuery("SELECT DISTINCT e FROM SavedGeneratorState e"
				+ " WHERE e.powerObjectDate = :dateToSearsch AND e.powerObjectId = :powerObjectId");
		
		query.setParameter("dateToSearsch", dateToSearsch);
		query.setParameter("powerObjectId", powerObjectId);
		result = query.getResultList();
		logger.debug("Requested: generator states for power station, returned {}.", result);
		
		return result;
	}
	
	@Override
	public void saveState(SavedGeneratorState state) {
		em.persist(state);
		logger.debug("Saved: {}.", state);
	}
}
