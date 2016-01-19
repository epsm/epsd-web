package com.epsm.epsdWeb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epsm.epsdWeb.domain.SavedConsumerState;

@Repository
public class SavedConsumerStateDaoImpl implements SavedConsumerStateDao{
	private Logger logger = LoggerFactory.getLogger(SavedConsumerStateDaoImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<SavedConsumerState> getStatesByNumber(long consumerId) {
		Query query = em.createQuery("SELECT c FROM SavedConsumerState c WHERE c.consumerId "
				+ "= :consumerId");
		query.setParameter("consumerId", consumerId);
		
		logger.debug("Requested: List<SavedConsumerState> for consumerId#{}.", consumerId);
		
		return (List<SavedConsumerState>)query.getResultList();
	}
	
	@Override
	public void saveState(SavedConsumerState state) {
		em.merge(state);
		
		logger.debug("Saved: {} consId#{}.", state.getClass().getSimpleName(),
				state.getConsumerId());
	}
}
