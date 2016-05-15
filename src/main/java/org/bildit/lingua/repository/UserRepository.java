package org.bildit.lingua.repository;

import java.util.List;
import org.bildit.lingua.model.User;

public interface UserRepository extends BaseRepository <User, Long>, UserRepositoryCustom {
	
	List<User> findAll();
	
	@SuppressWarnings("unchecked")
	User save(User user);
	
	/** Temporary method */
	void customMethod(User user);
	
}
