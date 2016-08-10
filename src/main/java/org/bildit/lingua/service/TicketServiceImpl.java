package org.bildit.lingua.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bildit.lingua.model.Language;
import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.model.User;
import org.bildit.lingua.model.Vote;
import org.bildit.lingua.repository.LanguageRepository;
import org.bildit.lingua.repository.TicketRepository;
import org.bildit.lingua.repository.UserRepository;
import org.bildit.lingua.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class TicketServiceImpl implements TicketService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
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
	 * This method returns all tickets of current user by username, 5 records per page.
	 */
	@Override
	public Page<Ticket> getAllTicketsByUsername(String username, String learningLanguage, Pageable pageable) {
		return ticketRepository.findAllByUserIdAndLearningLanguageOrderByLocalDateTimeDesc(userRepository.findUserByUsername(username).getId(), languageRepository.getOneByLanguageTitle(learningLanguage), pageable);
	}

	/**
	 * @author Mladen Todorovic
	 * @edit Bojan Aleksic
	 * Method: get list of all active user tickets by username
	 * */
	@Override
	public Page<Ticket> getAllActiveTicketsByUsername(String username, String learningLanguage, Pageable pageable) {
		return ticketRepository.findAllByUserIdAndLearningLanguageAndDeactivatedIsNullOrderByLocalDateTimeDesc(userRepository.findUserByUsername(username).getId(), languageRepository.getOneByLanguageTitle(learningLanguage), pageable);
	}
	/**
	 * @author Mladen Todorovic
	 * @edit Bojan Aleksic
	 * Method: get list of all deleted (deactivated) user tickets by username
	 * */
	@Override
	public Page<Ticket> getAllDeactivatedTicketsByUsername(String username, String learningLanguage, Pageable pageable) {
		return ticketRepository.findAllByUserIdAndLearningLanguageAndDeactivatedIsNotNullOrderByLocalDateTimeDesc(userRepository.findUserByUsername(username).getId(), languageRepository.getOneByLanguageTitle(learningLanguage), pageable);
	}
	/**
	 * @author Mladen Todorovic
	 * @edit Bojan Aleksic
	 * Method: get list of all moderated user tickets by username
	 * */
	@Override
	public Page<Ticket> getAllModeratedTicketsByUsername(String username, String learningLanguage, Pageable pageable) {
		return ticketRepository.findAllByUserIdAndLearningLanguageAndEditedTrueOrderByLocalDateTimeDesc(userRepository.findUserByUsername(username).getId(), languageRepository.getOneByLanguageTitle(learningLanguage), pageable);
	}

	/**
	 * @author Bojan Aleksic
	 * @edit Mladen Todorovic
	 * Save ticket into database
	 */
	@Override
	public Ticket saveTicket(Ticket ticket, String username) {
		User user = userRepository.findUserByUsername(username);
		ticket.setUser(user);
		ticket.setLocalDateTime(LocalDateTime.now());
		user.getTickets().add(ticket);
		Vote vote = new Vote(0,0);
		vote.setTicket(ticket);
		ticket.setTicketVotes(vote);
		ticket.setLearningLanguage(user.getForeignLanguage());
		ticket.setDomesticLanguage(user.getDomesticLanguage());
		return ticketRepository.saveAndFlush(ticket);
	}
	
	/**
	 * @author Mladen Todorovic
	 * Method: add vote-like to ticket by ticket-id and user's username
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
		vote = ticket.getTicketVotes();
		Set<User> listOfVotedUsers = vote.getVotedUsers();
		
		if (!listOfVotedUsers.contains(user)) {
			listOfVotedUsers.add(user);
			vote.incrementLikes();
			voteRepository.save(vote);
			return String.valueOf(vote.getLikes());
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
			return "your-own-ticket";
		}
		Vote vote = new Vote();
		vote = ticket.getTicketVotes();
		Set<User> listOfVotedUsers = vote.getVotedUsers();
		
		if (!listOfVotedUsers.contains(user)) {
			listOfVotedUsers.add(user);
			vote.incrementDislikes();
			voteRepository.save(vote);
			return String.valueOf(vote.getDislikes());
		} else {
			return "already-voted";
		}
	}
	
	/**
	 * @author Mladen Todorovic
	 * Method: update ticket's parameters using ticket-id
	 * */
	@Override
	public void updateTicket(String textDomestic, String textForeign, String category, LocalDateTime localDateTime, Long ticketId) {
		ticketRepository.update(textDomestic, textForeign, category, LocalDateTime.now(), ticketId);
	}
	
	/**
	 * @author Mladen Todorovic
	 * Method: delete a ticket and all its relations by ticket-id and user's username
	 * */
	@Override
	public void deleteTicket(Long id, String username) {	
		User user = userRepository.findUserByUsername(username);
		Ticket ticket = ticketRepository.findOneByUserAndId(user, id);
		Vote vote = voteRepository.findVoteByTicket(ticket);
		vote.getVotedUsers().clear();
		voteRepository.delete(vote);
		ticketRepository.delete(ticket);
	}
	
	/**
	 * @author Novislav Sekulic
	 * Method for deleting ticket by admin.
	 */
	@Override
	public void disableTicketByAdmin(Long id) {
		Ticket ticket = ticketRepository.findOne(id);
		ticket.setDeactivated(new Date());
		ticketRepository.save(ticket);
	}
	
	/**
	 * @author Novislav Sekulic
	 */
	@Override
	public void enableTicket(Long id) {
		Ticket ticket = ticketRepository.findOne(id);
		ticket.setDeactivated(null);
		ticketRepository.save(ticket);
	}
	
	/**
	 * @author Bojan Aleksic
	 * Method returns all tickets by provided category
	 */
	@Override
	public List<Ticket> getTicketsByCategory(String category) {
		return ticketRepository.findAllByCategory(category);
	}
	
	/**
	 * @author Bojan Aleksic
	 * Method returns tickets by User and his learning language.
	 */
	@Override
	public List<Ticket> getTicketsByUserAndLanguage(User user, Language language) {
		return ticketRepository.findAllByUserAndLearningLanguage(user, language);
	}

	/**
	 * @author Bojan Aleksic
	 * Method returns ticket by user, selected category, and
	 * by user's learning language.
	 */
	@Override
	public List<Ticket> getTicketsByUserCategoryAndLanguage(User user, String category, Language language) {
		return ticketRepository.findAllByUserAndCategoryAndLearningLanguage(user, category, language);
	}

	/**
	 * @author Bojan Aleksic
	 * Method returns everyone's tickets by specified learning language
	 * by the user.
	 */
	@Override
	public List<Ticket> getEveryonesTicketsByLanguage(Language language) {
		return ticketRepository.findAllByLearningLanguage(language);
	}

	/**
	 * @author Bojan Aleksic 
	 * Method returns tickets by specified category and user's learning language.
	 */
	@Override
	public List<Ticket> getTicketsByCategoryAndLanguage(String category, Language language) {
		return ticketRepository.findAllByCategoryAndLearningLanguage(category, language);
	}
	
	/**
	 * @author Novislav Sekulic
	 * Method returns all tickets sorted by dislikes value.
	 */
	@Override
	public Page<Ticket> getAllTicketsSortedByDislike(Pageable pageable) {
		return ticketRepository.getAllTicketOrderedByDislike(pageable);
	}

	/**
	 * @author Novislav Sekulic
	 * Method return all moderated tickets.
	 */
	@Override
	public Page<Ticket> getAllModeratedTickets(Pageable pageable) {
		return ticketRepository.findAllByEditedTrueAndDeactivatedIsNull(pageable);
	}
	
	/**
	 * @author Novislav Sekulic
	 * Return all deactivated tickets.
	 */
	@Override
	public Page<Ticket> getAllDeactivatedTickets(Pageable pageable) {
		return ticketRepository.findAllByDeactivatedNotNull(pageable);
	}
	
	/**
	 * @author Novislav Sekulic
	 * Return all tickets.
	 */
	@Override
	public Page<Ticket> findAll(Pageable pageable) {
		return ticketRepository.findAll(pageable);
	}
	
	/**
	 * @author Novislav Sekulic
	 * Return all tickets sorted by date.
	 */
	@Override
	public Page<Ticket> findAllOrderByDislike(Pageable pageable) {
		return ticketRepository.findAllByOrderByLocalDateTimeDesc(pageable);
	}
	
	/**
	 * @author Novislav Sekulic
	 * Return all liked sorted by likes.
	 */
	@Override
	public Page<Ticket> getAllTicketOrderedByLike(Pageable pageable) {
		return ticketRepository.getAllTicketOrderedByLike(pageable);
	}
	
	/**
	 * @author Novislav Sekulic
	 * Return all diabled tickets sorted by dislike.
	 */
	@Override
	public Page<Ticket> getAllDeactivatedSortedByDislike(Pageable pageable) {
		return ticketRepository.getAllDeactivatedSortedByDislike(pageable);
	}
	
	/**
	 * @author Novislav Sekulic
	 * Method for showing ticket for admin.
	 */
	@Override
	public ModelAndView getTicketsForAdmin(
			ModelAndView model, String urlRequest, Integer page, Pageable pageable) {
		
		Page<Ticket> tickets = null;
		
		if(urlRequest.equals("user-tickets-all")) {
			tickets = findAllOrderByDislike(pageable);
		} else if (urlRequest.equals("user-tickets-liked")) {
			tickets = getAllTicketOrderedByLike(pageable);
		} else if(urlRequest.equals("user-tickets-disliked")) {
			tickets = getAllTicketsSortedByDislike(pageable);
		} else if (urlRequest.equals("user-ticket-moderated")){
			tickets = getAllModeratedTickets(pageable);
		} else if(urlRequest.equals("user-ticket-deleted")){
			tickets = getAllDeactivatedSortedByDislike(pageable);
		}
				

		if(tickets != null && tickets.getContent().size() > 0) {
			model.addObject("tickets", tickets);
			model.addObject("totalPages", tickets.getTotalPages());
		}
		
		return model;
	}

	
}
