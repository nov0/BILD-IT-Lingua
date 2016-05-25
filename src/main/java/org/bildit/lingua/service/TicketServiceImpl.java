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
	/**
	 * @author Mladen Todorovic
	 * 
	 * Method for getting list of all active user tickets by username
	 * 
	 * */
	@Override
	public List<Ticket> getAllActiveTicketsByUsername(String username) {
		User user = userRepository.findUserByUsername(username);
		return ticketRepository.findAllByUserIdAndDeactivatedIsNull(user.getId());
	}
	/**
	 * @author Mladen Todorovic
	 * 
	 * Method for getting list of all deleted (deactivated) user tickets by username
	 * 
	 * */
	@Override
	public List<Ticket> getAllDeactivatedTicketsByUsername(String username) {
		User user = userRepository.findUserByUsername(username);
		return ticketRepository.findAllByUserIdAndDeactivatedIsNotNull(user.getId());
	}
	/**
	 * @author Mladen Todorovic
	 * 
	 * Method for getting list of all moderated user tickets by username
	 * 
	 * */
	@Override
	public List<Ticket> getAllModeratedTicketsByUsername(String username) {
		User user = userRepository.findUserByUsername(username);
		return ticketRepository.findAllByUserIdAndEditedTrue(user.getId());
	}
	
}
