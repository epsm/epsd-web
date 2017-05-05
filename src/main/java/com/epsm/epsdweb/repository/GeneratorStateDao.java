package com.epsm.epsdweb.repository;

import com.epsm.epsdweb.service.chartService.ValueSource;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class GeneratorStateDao {

	@PersistenceContext
	private EntityManager em;

	private final static String GET_GENERATION_QUERY =
			"SELECT NEW com.epsm.epsdweb.service.chartService.ValueSource(SUM(gs.generationInMW), pss.simulationTimeStamp) " +
			"   FROM SavedGeneratorState gs " +
			"   JOIN gs.savedPowerStationState pss " +
			"   WHERE pss.simulationTimeStamp >=:from " +
			"   AND pss.simulationTimeStamp <:to " +
			"GROUP BY pss.simulationTimeStamp " +
			"ORDER BY pss.simulationTimeStamp ";

	public List<ValueSource> getGeneration(LocalDateTime from, LocalDateTime to) {
		Query query = em.createQuery(GET_GENERATION_QUERY);
		query.setParameter("from", from);
		query.setParameter("to", to);

		return query.getResultList();
	}
}
