package org.bildit.lingua.repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @class LanguageRepositoryImpl
 * @author Mladen Todorovic
 * */
public class LanguageRepositoryImpl {
	
	@PersistenceContext
	private EntityManager entityManager;
	
}
