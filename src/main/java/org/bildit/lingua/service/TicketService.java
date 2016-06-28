package org.bildit.lingua.service;

import org.bildit.lingua.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketService extends BaseService<Ticket, Long> {

	Page<Ticket> getAllTicketsByUsername(String username, String learningLanguage, Pageable pageable);
	Page<Ticket> getAllActiveTicketsByUsername(String username, String learningLanguage, Pageable pageable);
	Page<Ticket> getAllDeactivatedTicketsByUsername(String username, String learningLanguage, Pageable pageable);
	Page<Ticket> getAllModeratedTicketsByUsername(String username, String learningLanguage, Pageable pageable);
	
	Ticket saveTicket(Ticket ticket, String username);
	
	void updateTicket(String textDomestic, String textForeign, String category, Long ticketId);
	
	String addLikeToTicket(Long id, String username);
	String addDislikeToTicket(Long id, String username);
	
	void deleteTicket(Long id, String username);
	
}
