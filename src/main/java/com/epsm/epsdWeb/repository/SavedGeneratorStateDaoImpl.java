package com.epsm.epsdWeb.repository;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<SavedGeneratorState> getStatesByPowerStationNumber(long powerStationId) {
		Query query = em.createQuery("SELECT c FROM SavedGeneratorState c WHERE c.powerStationId "
				+ "= :powerStationId");
		query.setParameter("powerStationId", powerStationId);
		logger.debug("Requested: List<SavedGeneratorState> for powerStationId#{}.", powerStationId);
		
		return (List<SavedGeneratorState>)query.getResultList();
	}

	@Override
	public void saveState(SavedGeneratorState state) {
		em.persist(state);
		logger.debug("Saved: {}.", state);
	}
}
