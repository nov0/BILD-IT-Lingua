package org.bildit.lingua.repository;

import org.bildit.lingua.model.User;

/**
 * 
 * @interface UserRepository
 * 
 * @author Mladen Todorovic
 * 
 * */

public interface UserRepository extends BaseRepository <User, Long>, UserRepositoryCustom {
	
	User findOneByUsername(String username);
	
	User getOneByUsername(String username);
	
}
