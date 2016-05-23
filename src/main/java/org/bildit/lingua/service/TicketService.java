package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.Ticket;

public interface TicketService extends BaseService<Ticket, Long> {

	List<Ticket> getAllTicketsByUserId(Long id);
	
}
