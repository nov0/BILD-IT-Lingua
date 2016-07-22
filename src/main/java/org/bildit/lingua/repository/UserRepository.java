package org.bildit.lingua.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.bildit.lingua.model.Language;
import org.bildit.lingua.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @interface UserRepository
 * @author Mladen Todorovic
 * */
public interface UserRepository extends BaseRepository <User, Long> {
	
	List<User> findAll();
	
	/** @author Mladen Todorovic */
	User findUserByUsername(String username);
	
	/** @author Novislav Sekulic */
	@Query("SELECT CASE WHEN COUNT(baseUser) > 0 THEN 'true' ELSE 'false' END FROM BaseUser baseUser WHERE baseUser.username = ?1")
	boolean existByUsername(String username);
	
	/**
	 * @author Bojan Aleksic
	 * @param foreignLanguage
	 * @param id
	 */
	@Modifying // This tells spring-data-jpa to execute update
	@Transactional // @Modifying annotation requires @Transactional in order to work
	@Query("update User u set u.foreignLanguage = ?1 where u.id = ?2")
	void updateForeignLanguageForUser(Language foreignLanguage, Long id);
	
	/** @author Mladen Todorovic */
	@Query("SELECT u FROM User u WHERE u.votingBan = 1 OR u.username = ?1 OR u.firstName = ?2 OR u.lastName = ?3 ORDER BY firstName ASC")
	List<User> searchUsersByVotingBan(String username, String firstName, String lastName);
	
	/** @author Mladen Todorovic */
	@Query("SELECT u FROM User u WHERE u.addingBan = 1 OR u.username = ?1 OR u.firstName = ?2 OR u.lastName = ?3 ORDER BY firstName ASC")
	List<User> searchUsersByAddingBan(String username, String firstName, String lastName);
	
	/** @author Mladen Todorovic */
	@Query("SELECT u FROM User u WHERE u.enabled = 0 OR u.username = ?1 OR u.firstName = ?2 OR u.lastName = ?3 ORDER BY firstName ASC")
	List<User> searchUsersByLoginBan(String username, String firstName, String lastName);
	
	/** @author Mladen Todorovic */
	@Query("SELECT u FROM User u WHERE u.username = ?1 OR u.firstName = ?2 OR u.lastName = ?3 ORDER BY firstName ASC")
	List<User> findAllByUsernameOrFirstNameOrLastName(String username, String firstName, String lastName);
	
}
