package org.bildit.lingua.repository;

import java.util.List;
import org.bildit.lingua.model.User;
import org.springframework.data.jpa.repository.Query;

/**
 * 
 * @interface  UserRepository
 * 
 * @author Mladen Todorovic
 * 
 * */

public interface UserRepository extends BaseRepository <User, Long> {
	
	List<User> findAll();
	
	@SuppressWarnings("unchecked")
	User save(User user);
	
	@Query("SELECT CASE WHEN COUNT(baseUser) > 0 THEN 'true' ELSE 'false' END FROM BaseUser baseUser WHERE baseUser.username = ?1")
	Boolean existByUsername(String username);
	
}
