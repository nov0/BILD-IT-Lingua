package org.bildit.lingua.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @class UserRepositoryImpl
 * @author Mladen Todorovic
 * */
public class UserRepositoryImpl implements UserRepositoryCustom {
    
	@PersistenceContext
	private EntityManager entityManager;
	
    /** Temporary unused method */
    @Override
    public String customUserMethod() {
    	return "";
    }
    
}
