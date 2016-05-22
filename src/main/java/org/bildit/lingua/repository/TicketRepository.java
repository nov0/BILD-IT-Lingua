package org.bildit.lingua.repository;

import java.util.List;

import org.bildit.lingua.model.Ticket;

public interface TicketRepository extends BaseRepository<Ticket, Long> {

	List<Ticket> findAll();
	
}
