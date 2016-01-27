package com.epsm.epsdWeb.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epsm.epsdWeb.domain.SavedConsumerState;

@Repository
public class SavedConsumerStateDaoImpl implements SavedConsumerStateDao{
	private Logger logger = LoggerFactory.getLogger(SavedConsumerStateDaoImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void saveState(SavedConsumerState state) {
		em.persist(state);
		
		logger.debug("Saved: {} cons.Id#{}.", state.getClass().getSimpleName(), state.getPowerObjectId());
	}
}
