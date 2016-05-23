package org.bildit.lingua.repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * @class LanguageRepositoryImpl
 * 
 * @author Mladen Todorovic
 * 
 * */

public class LanguageRepositoryImpl implements LanguageRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/** Temporary unused method */
    @Override
	public String customLanguageMethod() {
		return "";
	}
	
}
