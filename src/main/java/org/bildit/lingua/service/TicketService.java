package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.Language;
import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketService extends BaseService<Ticket, Long> {

	Page<Ticket> getAllTicketsByUsername(String username, String learningLanguage, Pageable pageable);
	Page<Ticket> getAllActiveTicketsByUsername(String username, String learningLanguage, Pageable pageable);
	Page<Ticket> getAllDeactivatedTicketsByUsername(String username, String learningLanguage, Pageable pageable);
	Page<Ticket> getAllModeratedTicketsByUsername(String username, String learningLanguage, Pageable pageable);
	Page<Ticket> getAllTicketsSortedByDislike(Pageable pageable);
	Page<Ticket> getAllModeratedTickets(Pageable pageable);
	Page<Ticket> getAllDeactivatedTickets(Pageable pageable);
	
	Ticket saveTicket(Ticket ticket, String username);
	
	void updateTicket(String textDomestic, String textForeign, String category, Long ticketId);
	
	String addLikeToTicket(Long id, String username);
	String addDislikeToTicket(Long id, String username);
	
	void deleteTicket(Long id, String username);
	void disableTicketByAdmin(Long id);
	void enableTicket(Long id);
	
	List<Ticket> getTicketsByCategory(String category);
	
	List<Ticket> getTicketsByUserAndLanguage(User user, Language language);
	List<Ticket> getTicketsByUserCategoryAndLanguage(User user, String category, Language language);
	List<Ticket> getEveryonesTicketsByLanguage(Language language);
	List<Ticket> getTicketsByCategoryAndLanguage(String category, Language language);
	
}
