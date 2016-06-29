package org.bildit.lingua.repository;

import java.util.List;

import org.bildit.lingua.model.Language;
import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @interface TicketRepository
 * @author Mladen Todorovic
 * */
public interface TicketRepository extends BaseRepository<Ticket, Long>, TicketRepositoryCustom {
	
	List<Ticket> findAll();
	Page<Ticket> findAllByUserIdAndDeactivatedIsNull(Long id, Pageable pageable);
	Page<Ticket> findAllByUserIdAndDeactivatedIsNotNull(Long id, Pageable pageable);
	Page<Ticket> findAllByUserIdAndEditedTrue(Long id, Pageable pageable);
	
	/* @author: Bojan Aleksic */
	Page<Ticket> findAllByUserId(Long id, Pageable pageable);
	
	/**
	 * @author Bojan Aleksic
	 * Obtain all tickets by this user, with specific category selected.
	 */
	@Query("SELECT t FROM Ticket t WHERE t.user = ?1 AND t.category = ?2")
	List<Ticket> findAllByUserAndCategory(User user, String category);
	
	/** @author Bojan Aleksic */
	List<Ticket> findAllByCategory(String category);
	Ticket findOneByUserAndId(User user, Long id);
	List<Ticket> findAllByUserAndLearningLanguage(User user, Language language);
	List<Ticket> findAllByUserAndCategoryAndLearningLanguage(User user, String category, Language language);
	List<Ticket> findAllByLearningLanguage(Language language);
	List<Ticket> findAllByCategoryAndLearningLanguage(String category, Language language);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Ticket t SET t.textDomestic = ?1, t.textForeign = ?2, t.category = ?3, t.edited = 1 WHERE t.id = ?4")
	void update(String textDomestic, String textForeign, String category, Long ticketId);
	
	
	/**
	 * @author goran
	 */
	@Query("SELECT ticket FROM Ticket ticket WHERE ticket.user = ?1 AND ticket.category = ?2 AND ticket.learningLanguage = ?3 AND ticket.deactivated IS NULL ORDER BY RAND()")
	List<Ticket> getMyTicketsForPractice(User user, String category, Language language, Pageable pageable);
	
	/**
	 * @author goran
	 */
	@Query("SELECT ticket FROM Ticket ticket WHERE ticket.user = ?1 AND ticket.learningLanguage = ?2 AND ticket.deactivated IS NULL ORDER BY RAND()")
	List<Ticket> getMyTicketsForPractice(User user, Language language, Pageable pageable);
	
	/**
	 * @author goran
	 */
	@Query("SELECT ticket FROM Ticket ticket WHERE ticket.category = ?1 AND ticket.learningLanguage = ?2 AND ticket.deactivated IS NULL ORDER BY RAND()")
	List<Ticket> getTicketsForPractice(String category, Language language, Pageable pageable);
	
	/**
	 * @author goran
	 */
	@Query("SELECT ticket FROM Ticket ticket WHERE ticket.learningLanguage = ? AND ticket.deactivated IS NULL ORDER BY RAND()")
	List<Ticket> getTicketsForPractice(Language language, Pageable pageable);
	
}
