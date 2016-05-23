package org.bildit.lingua.repository;

import java.util.List;
import org.bildit.lingua.model.User;
import org.springframework.data.jpa.repository.Query;

/**
 * 
 * @interface UserRepository
 * 
 * @author Mladen Todorovic
 * 
 * */

public interface UserRepository extends BaseRepository <User, Long>, UserRepositoryCustom {
	
	List<User> findAll();
	User findUserByUsername(String username);
	
	@Query("SELECT CASE WHEN COUNT(baseUser) > 0 THEN 'true' ELSE 'false' END FROM BaseUser baseUser WHERE baseUser.username = ?1")
	boolean existByUsername(String username);
	
}
