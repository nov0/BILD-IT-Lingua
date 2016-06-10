package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.model.Vote;
import org.bildit.lingua.repository.TicketRepository;
import org.bildit.lingua.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @class VoteServiceImpl
 * @author Mladen Todorovic
 * */
public class VoteServiceImpl implements VoteService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	
	@Override
	public List<Vote> getAll() {
		return voteRepository.findAll();
	}

	@Override
	public Vote getOne(Long id) {
		return voteRepository.findOne(id);
	}
	
	@Override
	public void delete(Long id) {
		voteRepository.delete(id);
	}

	@Override
	public Vote findVoteByTicketId(Long id) {
		Ticket ticket = ticketRepository.findOne(id);
		return voteRepository.findVoteByTicket(ticket);
	}
	
}
