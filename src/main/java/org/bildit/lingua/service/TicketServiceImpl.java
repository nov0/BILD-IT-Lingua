package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.model.User;
import org.bildit.lingua.repository.TicketRepository;
import org.bildit.lingua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Ticket> getAll() {
		return ticketRepository.findAll();
	}

	@Override
	public Ticket getOne(Long id) {
		return ticketRepository.getOne(id);
	}

	@Override
	public void delete(Long id) {
		ticketRepository.delete(id);
	}

	@Override
	public List<Ticket> getAllTicketsByUsername(String username) {
		User user = userRepository.findUserByUsername(username);
		return ticketRepository.findAllByUserId(user.getId());
	}

	@Override
	public List<Ticket> getActiveTickets(String username) {
		//User user = userRepository.findUserByUsername(username);
		return null;
	}

	@Override
	public List<Ticket> getDeletedTickets(String username) {
		//User user = userRepository.findUserByUsername(username);
		return null;
	}

	@Override
	public List<Ticket> getModeratedTickets(String username) {
		//User user = userRepository.findUserByUsername(username);
		return null;
	}
	
	
	
}
