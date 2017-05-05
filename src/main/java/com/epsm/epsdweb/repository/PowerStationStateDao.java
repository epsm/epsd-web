package com.epsm.epsdweb.repository;

import com.epsm.epsdweb.domain.SavedPowerStationState;
import com.epsm.epsdweb.service.chartService.ValueSource;
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

	private final static String GET_FREQUENCY_QUERY =
			"SELECT NEW com.epsm.epsdweb.service.chartService.ValueSource(MIN(pss.frequency), pss.simulationTimeStamp) " +
			"   FROM SavedPowerStationState pss " +
			"   WHERE pss.simulationTimeStamp >=:from " +
			"   AND pss.simulationTimeStamp <:to " +
			"GROUP BY pss.simulationTimeStamp " +
			"ORDER BY pss.simulationTimeStamp ";

	private Logger logger = LoggerFactory.getLogger(PowerStationStateDao.class);
	
	@PersistenceContext
	private EntityManager em;

	public void saveStates(List<SavedPowerStationState> states) {
		states.forEach(st -> em.persist(st));
		logger.debug("Saved states: {}.", states);
	}

	public List<ValueSource> getFrequency(LocalDateTime from, LocalDateTime to) {
		Query query = em.createQuery(GET_FREQUENCY_QUERY);
		query.setParameter("from", from);
		query.setParameter("to", to);

		return query.getResultList();
	}
}
