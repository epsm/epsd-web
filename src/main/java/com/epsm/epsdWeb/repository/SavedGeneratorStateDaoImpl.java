package com.epsm.epsdWeb.repository;

import java.sql.Date;
import java.time.LocalDate;

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
	public void saveState(SavedGeneratorState state) {
		em.persist(state);
		logger.debug("Saved: {}.", state);
	}
}
