package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketService extends BaseService<Ticket, Long> {

//	List<Ticket> getAllTicketsByUsername(String username, int pageNumber);
	Page<Ticket> getAllTicketsByUsername(String username, Pageable pageable);
	List<Ticket> getAllActiveTicketsByUsername(String username);
	List<Ticket> getAllDeactivatedTicketsByUsername(String username);
	List<Ticket> getAllModeratedTicketsByUsername(String username);
	
	Ticket saveTicket(Ticket ticket, String username);
	
	void updateTicket(String textDomestic, String textForeign, String category, Long ticketId);
	
	String addLikeToTicket(Long id, String username);
	String addDislikeToTicket(Long id, String username);
	
	void deleteTicket(Long id, String username);
	
}
