package com.epsm.epsdweb.repository;

import com.epsm.epsdweb.domain.SavedPowerStationState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PowerStationStateDao {

	private Logger logger = LoggerFactory.getLogger(PowerStationStateDao.class);
	
	@PersistenceContext
	private EntityManager em;

	public void saveStates(List<SavedPowerStationState> states) {
		states.stream().forEach(st -> em.persist(st));
		logger.debug("Saved states: {}.", states);
	}
}
