package org.bildit.lingua.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.bildit.lingua.model.Language;
import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 
 * @interface TicketRepository
 * 
 * @author Mladen Todorovic
 * 
 * */
public interface TicketRepository extends BaseRepository<Ticket, Long>, TicketRepositoryCustom {

	List<Ticket> findAll();
	List<Ticket> findAllByUserId(Long id);
	List<Ticket> findAllByUserIdAndDeactivatedIsNull(Long id);
	List<Ticket> findAllByUserIdAndDeactivatedIsNotNull(Long id);
	List<Ticket> findAllByUserIdAndEditedTrue(Long id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Ticket t SET t.textDomestic = ?1, t.textForeign = ?2, t.category = ?3, t.edited = 1 WHERE t.id = ?4")
	void update(String textDomestic, String textForeign, String category, Long ticketId);
	
	
	/**
	 * @author goran
	 */
	@Query("SELECT ticket FROM Ticket ticket WHERE ticket.user = ?1 AND ticket.category = ?2 AND ticket.learningLanguage = ?3 ORDER BY RAND()")
	List<Ticket> getMyTicketsForPractice(User user, String category, Language language, Pageable pageable);
	
	/**
	 * @author goran
	 */
	@Query("SELECT ticket FROM Ticket ticket WHERE ticket.category = ?1 AND ticket.learningLanguage = ?2 ORDER BY RAND()")
	List<Ticket> getTicketsForPractice(String category, Language language, Pageable pageable);

	
}
