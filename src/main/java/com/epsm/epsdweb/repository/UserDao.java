package com.epsm.epsdweb.repository;

import com.epsm.epsdweb.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDao {

	@PersistenceContext
	private EntityManager em;
	
	public void saveUser(User user) {
		em.persist(user);
	}

	public List<User> findByEmail(String email) {
		Query query = em.createQuery("SELECT e FROM User e WHERE e.email = :email");
		query.setParameter("email", email);
		
		return query.getResultList();
	}
}
