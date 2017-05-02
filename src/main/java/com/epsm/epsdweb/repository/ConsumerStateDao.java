package com.epsm.epsdweb.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epsm.epsdweb.domain.SavedConsumerState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConsumerStateDao {

	private Logger logger = LoggerFactory.getLogger(ConsumerStateDao.class);
	
	@PersistenceContext
	private EntityManager em;
	
	public void saveStates(List<SavedConsumerState> states) {
		states.stream().forEach(st -> em.persist(st));
		logger.debug("Saved consumer states: {}.", states);
	}
}
