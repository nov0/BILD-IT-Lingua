package org.bildit.lingua.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @class UserRepositoryImpl
 * @author Mladen Todorovic
 * */
public class UserRepositoryImpl {
    
	@PersistenceContext
	private EntityManager entityManager;
    
}
