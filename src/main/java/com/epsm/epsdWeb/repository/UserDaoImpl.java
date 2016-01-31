package com.epsm.epsdWeb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.epsm.epsdWeb.domain.User;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void saveUser(User user) {
		em.persist(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByEmail(String email) {
		Query query = em.createQuery("SELECT e FROM User e WHERE e.email = :email");
		query.setParameter("email", email);
		
		return query.getResultList();
	}
}
