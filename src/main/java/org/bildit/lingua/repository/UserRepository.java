package org.bildit.lingua.repository;

import org.bildit.lingua.model.User;
import org.springframework.data.jpa.repository.Query;

/**
 * 
 * @interface  UserRepository
 * 
 * @author Mladen Todorovic
 * 
 * */

public interface UserRepository extends BaseRepository <User, Long>, UserRepositoryCustom {
	
	User findOneByUsername(String username);
	
	User getOneByUsername(String username);
	
	@Query("SELECT CASE WHEN COUNT(baseUser) > 0 THEN 'true' ELSE 'false' END FROM BaseUser baseUser WHERE baseUser.username = ?1")
	Boolean existByUsername(String username);
	
}
