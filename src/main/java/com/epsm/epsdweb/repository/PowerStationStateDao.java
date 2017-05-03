package com.epsm.epsdweb.repository;

import com.epsm.epsdweb.domain.SavedPowerStationState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PowerStationStateDao {

	private Logger logger = LoggerFactory.getLogger(PowerStationStateDao.class);
	
	@PersistenceContext
	private EntityManager em;

	private final static String GET_STATES_QUERY =
			"SELECT spss FROM SavedPowerStationState spss " +
			"   WHERE spss.simulationTimeStamp >=:from " +
			"   AND spss.simulationTimeStamp <:to " +
			"ORDER BY spss.simulationTimeStamp ";

	public void saveStates(List<SavedPowerStationState> states) {
		states.forEach(st -> em.persist(st));
		logger.debug("Saved states: {}.", states);
	}

	public List<SavedPowerStationState> getStates(LocalDateTime from, LocalDateTime to) {
		Query query = em.createQuery(GET_STATES_QUERY);
		query.setParameter("from", from);
		query.setParameter("to", to);

		return query.getResultList();
	}
}
