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
public interface TicketRepository extends BaseRepository<Ticket, Long> {
	
	List<Ticket> findAll();
	/** @author Mladen Todorovic */
	Page<Ticket> findAllByLearningLanguage(Language learningLanguage, Pageable pageable);
	/** @author Mladen Todorovic */
	Page<Ticket> findAllByUserIdAndLearningLanguageAndDeactivatedIsNull(Long id, Language learningLanguage, Pageable pageable);
	/** @author Mladen Todorovic */
	Page<Ticket> findAllByUserIdAndLearningLanguageAndDeactivatedIsNotNull(Long id, Language learningLanguage, Pageable pageable);
	/** @author Mladen Todorovic */
	Page<Ticket> findAllByUserIdAndLearningLanguageAndEditedTrue(Long id, Language learningLanguage, Pageable pageable);
	
	/* @author: Bojan Aleksic */
	Page<Ticket> findAllByUserId(Long id, Pageable pageable);
	
	/** @author Mladen Todorovic */
	Page<Ticket> findAllByUserIdAndLearningLanguage(Long id, Language learningLanguage, Pageable pageable);
	
	/** @author Mladen Todorovic */
	Ticket findOneByUserAndId(User user, Long id);
	
	/** @author Mladen Todorovic */
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Ticket t SET t.textDomestic = ?1, t.textForeign = ?2, t.category = ?3, t.edited = 1 WHERE t.id = ?4")
	void update(String textDomestic, String textForeign, String category, Long ticketId);	
	
	/** @author Goran Arsenic */
	@Query("SELECT ticket FROM Ticket ticket WHERE ticket.user = ?1 AND ticket.category = ?2 AND ticket.learningLanguage = ?3 AND ticket.deactivated IS NULL ORDER BY RAND()")
	List<Ticket> getMyTicketsForPractice(User user, String category, Language language, Pageable pageable);
	
	/** @author Goran Arsenic */
	@Query("SELECT ticket FROM Ticket ticket WHERE ticket.user = ?1 AND ticket.learningLanguage = ?2 AND ticket.deactivated IS NULL ORDER BY RAND()")
	List<Ticket> getMyTicketsForPractice(User user, Language language, Pageable pageable);
	
	/** @author Goran Arsenic */
	@Query("SELECT ticket FROM Ticket ticket WHERE ticket.category = ?1 AND ticket.learningLanguage = ?2 AND ticket.deactivated IS NULL ORDER BY RAND()")
	List<Ticket> getTicketsForPractice(String category, Language language, Pageable pageable);
	
	/** @author Goran Arsenic */
	@Query("SELECT ticket FROM Ticket ticket WHERE ticket.learningLanguage = ?1 AND ticket.deactivated IS NULL ORDER BY RAND()")
	List<Ticket> getTicketsForPractice(Language language, Pageable pageable);

	
}
