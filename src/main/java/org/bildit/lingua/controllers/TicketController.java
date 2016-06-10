package org.bildit.lingua.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.service.TicketService;
import org.bildit.lingua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TicketController {
	
	private static final String TICKETS = "ticketsList";
	private static final String HOME = "home";
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * @author Bojan Aleksic
	 * @param principal
	 * @return
	 * Method receives all tickets by current user, and sends back data
	 * in the JSON format to the server
	 */
	@RequestMapping("/ticket-all")
	@ResponseBody
	public Map<String, Object> getAllTickets(Principal principal) {
		Map<String, Object> map = new HashMap<>();
		map.put(TICKETS, ticketService.getAllTicketsByUsername(principal.getName()));
		return map;
	}
	
	@RequestMapping("/ticket-active")
	@ResponseBody
	public Map<String, Object> getActiveTickets(Principal principal) {
		Map<String, Object> map = new HashMap<>();
		map.put(TICKETS, ticketService.getAllActiveTicketsByUsername(principal.getName()));
		return map;
	}
	
	@RequestMapping("/ticket-deleted")
	@ResponseBody
	public Map<String, Object> getDeletedTickets(Principal principal) {
		Map<String, Object> map = new HashMap<>();
		map.put(TICKETS, ticketService.getAllDeactivatedTicketsByUsername(principal.getName()));
		return map;
	}
	
	@RequestMapping("/ticket-moderated")
	@ResponseBody
	public Map<String, Object> getModeratedTickets(Principal principal) {
		Map<String, Object> map = new HashMap<>();
		map.put(TICKETS, ticketService.getAllModeratedTicketsByUsername(principal.getName()));
		return map;
	}
	
	/**
	 * @author Bojan Aleksic
	 * @param languageTitle
	 * @param principal
	 * @return
	 * Updating user's foreign (learning) language
	 */
	@RequestMapping(value="/set-foreign-language", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, String> setForeignLanguage(@RequestBody String languageTitle, Principal principal) {
		Map<String, String> map = new HashMap<>();
		userService.setForeignLanguageForUser(principal.getName(), languageTitle);
		map.put("languageTitle", languageTitle);
		return map;
	}
	
	/**
	 * @author Bojan Aleksic
	 * @param ticket
	 * @param principal
	 * @return
	 */
	@RequestMapping("/create-ticket")
	public String createNewTicket(@ModelAttribute("ticket") Ticket ticket, Principal principal) {
		ticketService.saveTicket(ticket, principal.getName());
		return HOME;
	}
	
	/**
	 * @author Bojan Aleksic
	 * @param ticket
	 * @return
	 */
	@RequestMapping("/edit-ticket")
	public String editTicket(@ModelAttribute("ticket") Ticket ticket) {
		ticketService.updateTicket(ticket.getTextDomestic(), ticket.getTextForeign(), ticket.getCategory(), ticket.getId());
		return HOME;
	}
	
	/**
	 * @author Mladen Todorovic
	 * Method: add like to ticket by ticket id and user's username
	 */
	@RequestMapping("/add-like") // param ticketId is actually type long and username could be principal
	public String addLike(@RequestParam("ticketId") String ticketId, @RequestParam("username") String username, Model model) {
		Long id = Long.parseLong(ticketId); // this is for testing purposes
		return ticketService.addLikeToTicket(id, username, model);
	}
	
	/**
	 * @author Mladen Todorovic
	 * Method: add like to ticket by ticket id and user's username
	 */
	@RequestMapping("/add-dislike") // param ticketId is actually type long and username could be principal
	public String addDislike(@RequestParam("ticketId") String ticketId, @RequestParam("username") String username, Model model) {
		Long id = Long.parseLong(ticketId); // this is for testing purposes
		return ticketService.addDislikeToTicket(id, username, model);
	}
	
	/** For testing purposes */
	@RequestMapping("/test")
	public String goToTest() {
		return "test";
	}
	
}