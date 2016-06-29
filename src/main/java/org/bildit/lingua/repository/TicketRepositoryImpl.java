package org.bildit.lingua.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @class TicketRepositoryImpl
 * @author Mladen Todorovic
 * */
public class TicketRepositoryImpl {
	
	@PersistenceContext
	private EntityManager entityManager;
	
}
