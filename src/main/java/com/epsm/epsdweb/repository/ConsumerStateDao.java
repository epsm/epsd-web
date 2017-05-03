package com.epsm.epsdweb.repository;

import com.epsm.epsdweb.domain.SavedConsumerState;
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
public class ConsumerStateDao {

	private Logger logger = LoggerFactory.getLogger(ConsumerStateDao.class);
	
	@PersistenceContext
	private EntityManager em;

	private final static String GET_STATES_QUERY =
			"SELECT NEW com.epsm.epsdweb.service.chartService.ValueSource(SUM(cs.loadInMW), cs.simulationTimeStamp) " +
			"   FROM SavedConsumerState cs " +
			"   WHERE cs.simulationTimeStamp >=:from " +
			"   AND cs.simulationTimeStamp <:to " +
			"GROUP BY cs.simulationTimeStamp " +
			"ORDER BY cs.simulationTimeStamp ";

	public void saveStates(List<SavedConsumerState> states) {
		states.forEach(st -> em.persist(st));
		logger.debug("Saved consumer states: {}.", states);
	}

	public List<ValueSource> getStates(LocalDateTime from, LocalDateTime to) {
		Query query = em.createQuery(GET_STATES_QUERY);
		query.setParameter("from", from);
		query.setParameter("to", to);

		return query.getResultList();
	}
}
