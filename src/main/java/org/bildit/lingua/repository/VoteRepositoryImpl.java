package org.bildit.lingua.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @class VoteRepositoryImpl
 * @author Mladen Todorovic
 * */
public class VoteRepositoryImpl {
	
    @PersistenceContext
    private EntityManager entityManager;
    
}
