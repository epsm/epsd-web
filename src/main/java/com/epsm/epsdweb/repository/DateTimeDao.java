package com.epsm.epsdweb.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class DateTimeDao {

	@PersistenceContext
	private EntityManager em;

	private static final String GET_DATE_QUERY =
			"SELECT min(simulation_timestamp) AS date " +
			"   FROM ( "+
			"       SELECT max(simulation_timestamp) AS simulation_timestamp FROM consumer_state " +
			"       UNION " +
			"       SELECT max(simulation_timestamp) AS simulation_timestamp FROM power_station_state) sub";

	
	public LocalDateTime getLastDate() {
		Query query = em.createNativeQuery(GET_DATE_QUERY);

		return ((Timestamp) query.getSingleResult()).toLocalDateTime();
	}
}
