package org.bildit.lingua.repository;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * 
 * @interface  CustomUserRepository
 * 
 * @author Mladen Todorovic
 * 
 * */

@NoRepositoryBean
public interface CustomUserRepository {
	
	/** Temporary unused method */
	public String customUserMethod();
	
}
