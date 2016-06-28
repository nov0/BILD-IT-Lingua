package org.bildit.lingua.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @class AdminRepositoryImpl
 * @author Mladen Todorovic
 * */
public class AdminRepositoryImpl {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/** Configure the entity manager to be used */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	 
}