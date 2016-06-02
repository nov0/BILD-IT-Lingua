package org.bildit.lingua.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.model.User;
import org.bildit.lingua.model.Vote;
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

	/** 
	 * @author Bojan Aleksic
	 * This method returns all tickets of current user by username
	 */
	@Override
	public List<Ticket> getAllTicketsByUsername(String username) {
		return userRepository.findUserByUsername(username).getTickets();
	}

	/**
	 * @author Mladen Todorovic
	 * 
	 * Method for getting list of all active user tickets by username
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
	 * */
	@Override
	public List<Ticket> getAllModeratedTicketsByUsername(String username) {
		User user = userRepository.findUserByUsername(username);
		return ticketRepository.findAllByUserIdAndEditedTrue(user.getId());
	}

	/**
	 * @author Bojan Aleksic
	 * Save ticket into database
	 */
	@Override
	public Ticket saveTicket(Ticket ticket, String username) {
		User user = userRepository.findUserByUsername(username);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
		String date = sdf.format(new Date());
		ticket.setUser(user);
		ticket.setDateCreated(date);
		user.getTickets().add(ticket);
		Vote vote = new Vote();
		vote.setVoteValue(0);
		ticket.getVotesUp().add(vote);
		ticket.getVotesDown().add(vote);
		return ticketRepository.save(ticket);
	}
	/**
	 * @author Mladen Todorovic
	 * 
	 * Method for updating inputed parameters using ticket id
	 * */
	@Override
	public Ticket updateTicket(Long ticketId, String textDom, String textFor, String category) {
		return ticketRepository.update(ticketId, textDom, textFor, category);
	}
	
}
