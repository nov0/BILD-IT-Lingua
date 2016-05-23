package org.bildit.lingua.repository;

import org.bildit.lingua.model.Ticket;

/**
 * 
 * @interface TicketRepository
 * 
 * @author Mladen Todorovic
 * 
 * */

public interface TicketRepository extends BaseRepository <Ticket, Long>, TicketRepositoryCustom {
	
}
