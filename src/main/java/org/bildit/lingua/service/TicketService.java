package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.Ticket;

public interface TicketService extends BaseService<Ticket, Long> {

	List<Ticket> getAllTicketsByUsername(String username);
	List<Ticket> getAllActiveTicketsByUsername(String username);
	List<Ticket> getAllDeactivatedTicketsByUsername(String username);
	List<Ticket> getAllModeratedTicketsByUsername(String username);
	Ticket saveTicket(Ticket ticket, String username);
	void updateTicket(String textDomestic, String textForeign, String category, Long ticketId);
	
}
