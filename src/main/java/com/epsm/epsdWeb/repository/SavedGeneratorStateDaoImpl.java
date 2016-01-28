package com.epsm.epsdWeb.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	public void saveState(SavedGeneratorState state) {
		em.persist(state);
		logger.debug("Saved: {}.", state);
	}
}
