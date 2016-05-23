package org.bildit.lingua.repository;

import java.util.List;

import org.bildit.lingua.model.Ticket;

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
	
}
