package org.bildit.lingua.repository;

import java.time.LocalDateTime;
import java.util.Date;
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
	Page<Ticket> findAllByUserIdAndLearningLanguageAndDeactivatedIsNullOrderByLocalDateTimeDesc(Long id, Language learningLanguage, Pageable pageable);
	/** @author Mladen Todorovic */
	Page<Ticket> findAllByUserIdAndLearningLanguageAndDeactivatedIsNotNullOrderByLocalDateTimeDesc(Long id, Language learningLanguage, Pageable pageable);
	/** @author Mladen Todorovic */
	Page<Ticket> findAllByUserIdAndLearningLanguageAndEditedTrueOrderByLocalDateTimeDesc(Long id, Language learningLanguage, Pageable pageable);
	
	/** @author: Bojan Aleksic */
	Page<Ticket> findAllByUserId(Long id, Pageable pageable);
	
	/** @author Mladen Todorovic */
	Page<Ticket> findAllByUserIdAndLearningLanguageOrderByLocalDateTimeDesc(Long id, Language learningLanguage, Pageable pageable);
	
	/** @author Novislav Sekulic */
	Page<Ticket> findAllByEditedTrueAndDeactivatedIsNull(Pageable pageable);
	
	/** @author Novislav Sekulic */
	Page<Ticket> findAllByOrderByLocalDateTimeDesc(Pageable pageable);
	
	/** @author Novislav Sekulic */
	Page<Ticket> findAllByDeactivatedNotNull(Pageable pageable);
	
	
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
	
	/**
	 * @author Bojan Aleksic
	 * @param language
	 * @param pageable
	 * @return
	 */
	@Query("SELECT t FROM Ticket t "
		 + "WHERE t.learningLanguage = ?1 "
		 + "GROUP BY t.ticketVotes "
		 + "ORDER BY "
		 + "SUM(t.ticketVotes.likes - t.ticketVotes.dislikes) "
		 + "DESC")
	List<Ticket> findAllByLanguageAndTicketRating(Language language, Pageable pageable);
	
	/** @author Mladen Todorovic */
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Ticket t SET t.textDomestic = ?1, t.textForeign = ?2, t.category = ?3, t.localDateTime = ?4, t.edited = 1 WHERE t.id = ?5")
	void update(String textDomestic, String textForeign, String category, LocalDateTime localDateTime, Long ticketId);	
	
	/** @author Goran Arsenic **/
	@Query("SELECT t FROM Ticket t WHERE t.user = ?1 AND t.category = ?2 AND t.learningLanguage = ?3 AND t.domesticLanguage = ?4 AND t.deactivated IS NULL ORDER BY RAND()")
	List<Ticket> getMyTicketsForPractice(User user, String category, Language learningLanguage, Language domesticLanguage, Pageable pageable);
	
	/** @author Goran Arsenic **/
	@Query("SELECT t FROM Ticket t WHERE t.user = ?1 AND t.learningLanguage = ?2 AND t.domesticLanguage = ?3 AND t.deactivated IS NULL ORDER BY RAND()")
	List<Ticket> getMyTicketsForPractice(User user, Language learningLanguage, Language domesticLanguage, Pageable pageable);
	
	/** @author Goran Arsenic **/
	@Query("SELECT t FROM Ticket t WHERE t.category = ?1 AND t.learningLanguage = ?2 AND t.domesticLanguage = ?3 AND t.deactivated IS NULL ORDER BY RAND()")
	List<Ticket> getTicketsForPractice(String category, Language learningLanguage, Language domesticLanguage, Pageable pageable);
	
	/** @author Goran Arsenic **/
	@Query("SELECT t FROM Ticket t WHERE t.learningLanguage = ?1 AND t.domesticLanguage = ?2 AND t.deactivated IS NULL ORDER BY RAND()")
	List<Ticket> getTicketsForPractice(Language learningLanguage, Language domesticLanguage, Pageable pageable);
	
	/** @author Goran Arsenic **/
	@Query("SELECT COUNT(t) FROM Ticket t WHERE t.learningLanguage = ?1")
	int getNumberOfTicketsForLanguage(Language learningLanguage);

	/** @author Goran Arsenic **/
	@Query("SELECT t FROM Ticket t WHERE t.deactivated <= ?1")
	List<Ticket> getTicketsForScheduledDelete(Date date);

	/** @author Novislav Sekulic */
	@Query("SELECT t FROM Ticket t WHERE t.deactivated IS NULL ORDER BY t.ticketVotes.dislikes DESC")
	Page<Ticket> getAllTicketOrderedByDislike(Pageable pageable);
	
	/** @author Novislav Sekulic */
	@Query("SELECT t FROM Ticket t WHERE t.deactivated IS NULL ORDER BY t.ticketVotes.likes DESC")
	Page<Ticket> getAllTicketOrderedByLike(Pageable pageable);
	
	/** @author Novislav Sekulic */
	@Query("SELECT t FROM Ticket t WHERE t.deactivated IS NOT NULL ORDER BY t.ticketVotes.dislikes DESC")
	Page<Ticket> getAllDeactivatedSortedByDislike(Pageable pageable);
	
	/** @author Novislav Sekulic */
	@Query("SELECT SUM(t.ticketVotes.dislikes) FROM Ticket t WHERE t.user = ?1 AND t.learningLanguage = ?2")
	Integer getSumOfTicketDislikesFromUserAndLanguage(User user, Language language);
	
	/** @author Novislav Sekulic */
	@Query("SELECT SUM(t.ticketVotes.likes) FROM Ticket t WHERE t.user = ?1 AND t.learningLanguage = ?2")
	Integer getSumOfTicketLikesFromUserAndLanguage(User user, Language language);
	
}
