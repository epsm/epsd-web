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
	public Date getLastSaveDate(){
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
		logger.debug("Requested: List<SavedGeneratorState> for date {}, returned {}.", date, result);
	
		return result;
	}

	@Override
	public void saveState(SavedGeneratorState state) {
		em.persist(state);
		logger.debug("Saved: {}.", state);
	}
}
