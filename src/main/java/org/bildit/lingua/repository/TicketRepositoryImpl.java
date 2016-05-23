package org.bildit.lingua.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * @class TicketRepositoryImpl
 * 
 * @author Mladen Todorovic
 * 
 * */

public class TicketRepositoryImpl implements TicketRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/** Temporary unused method */
	public String customTicketMethod() {
		return "";
	}
	
}
