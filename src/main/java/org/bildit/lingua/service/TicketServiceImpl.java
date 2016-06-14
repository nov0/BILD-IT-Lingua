package org.bildit.lingua.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.model.User;
import org.bildit.lingua.model.Vote;
import org.bildit.lingua.repository.TicketRepository;
import org.bildit.lingua.repository.UserRepository;
import org.bildit.lingua.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@SuppressWarnings("unused")
	@Autowired
	private VoteRepository voteRepository;
	
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
	 * Method for getting list of all active user tickets by username
	 * */
	@Override
	public List<Ticket> getAllActiveTicketsByUsername(String username) {
		User user = userRepository.findUserByUsername(username);
		return ticketRepository.findAllByUserIdAndDeactivatedIsNull(user.getId());
	}
	/**
	 * @author Mladen Todorovic
	 * Method for getting list of all deleted (deactivated) user tickets by username
	 * */
	@Override
	public List<Ticket> getAllDeactivatedTicketsByUsername(String username) {
		User user = userRepository.findUserByUsername(username);
		return ticketRepository.findAllByUserIdAndDeactivatedIsNotNull(user.getId());
	}
	/**
	 * @author Mladen Todorovic
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
		Vote vote = new Vote(0);
		vote.setTicket(ticket);
		ticket.setLikes(vote);
		Vote vote2 = new Vote(0);
		vote2.setTicket(ticket);
		ticket.setDislikes(vote2);
		ticket.setLearningLanguage(user.getForeignLanguage());
		return ticketRepository.save(ticket);
	}
	
	/**
	 * @author Mladen Todorovic
	 * Method add vote-like to ticket by ticket-id and user's username
	 */
	@Override
	public String addLikeToTicket(Long ticketId, String username) {
		
		User user = userRepository.findUserByUsername(username);
		Ticket ticket = ticketRepository.getOne(ticketId);
		Ticket userTicket = ticketRepository.findOneByUserAndId(user, ticket.getId());
		if (userTicket == ticket) {
			return "your-own-ticket";
		}
		Vote vote = new Vote();
		vote = ticket.getLikes();
		Set<User> listOfVotedUsers = vote.getVotedUsers();
		
		if (!listOfVotedUsers.contains(user)) {
			listOfVotedUsers.add(user);
			vote.incrementVoteValue();
//			voteRepository.save(vote);
			return String.valueOf(ticket.getLikes().getVoteValue()); // <-- edited
		} else {
			return "already-voted";
		}
	}
	
	/**
	 * @author Mladen Todorovic
	 * Method: add vote-dislike to ticket by ticket-id and user's username
	 */
	@Override
	public String addDislikeToTicket(Long ticketId, String username) {
		
		User user = userRepository.findUserByUsername(username);
		Ticket ticket = ticketRepository.getOne(ticketId);
		Ticket userTicket = ticketRepository.findOneByUserAndId(user, ticket.getId());
		if (userTicket == ticket) {
			return "test";
		}
		Vote vote = new Vote();
		vote = ticket.getDislikes();
		Set<User> listOfVotedUsers = vote.getVotedUsers();
		
		if (!listOfVotedUsers.contains(user)) {
			listOfVotedUsers.add(user);
			vote.incrementVoteValue();
//			voteRepository.save(vote);
			return String.valueOf(ticket.getDislikes().getVoteValue()); // <-- edited
		} else {
			return "test";
		}
	}
	
	/**
	 * @author Mladen Todorovic
	 * Method for updating inputed parameters using ticket-id
	 * */
	@Override
	public void updateTicket(String textDomestic, String textForeign, String category, Long ticketId) {
		ticketRepository.update(textDomestic, textForeign, category, ticketId);
	}
	
}
