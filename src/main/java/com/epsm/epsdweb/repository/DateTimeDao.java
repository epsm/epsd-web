package com.epsm.epsdweb.repository;

import com.epsm.epsdweb.domain.SavedConsumerState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DateTimeDao {

	@PersistenceContext
	private EntityManager em;

	private static final String DATE_QUERY =
			"SELECT min(simulation_timestamp) AS date " +
			"   FROM ( "+
			"       SELECT max(simulation_timestamp) AS simulation_timestamp FROM consumer_state " +
			"       UNION " +
			"       SELECT max(simulation_timestamp) AS simulation_timestamp FROM power_station_state) sub";

	
	public LocalDateTime getLastDate() {
		Query query = em.createNativeQuery(DATE_QUERY);

		return ((Timestamp) query.getSingleResult()).toLocalDateTime();
	}
}
