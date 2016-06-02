package org.bildit.lingua.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.service.TicketService;
import org.bildit.lingua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TicketController {
	
	private static final String TICKETS = "ticketsList";
	
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
	public String createNewTicket(@ModelAttribute("ticket") @Valid Ticket ticket, BindingResult result, Principal principal) {
		if(result.hasErrors()) {
			// Handle errors
		}
		ticketService.saveTicket(ticket, principal.getName());
		return "home";
	}
	
	/**
	 * @author Bojan Aleksic
	 * @param id
	 * @return
	 * Not working at the moment. Still in proces...
	 */
	@RequestMapping("/ticket-edit")
	@ResponseBody
	public Map<String, Object> editTicket(@RequestParam("id") Long id) {
		Map<String, Object> map = new HashMap<>();
		map.put("ticketEdit", ticketService.getOne(id));
		System.out.println("TEST: " + ticketService.getOne(id).getId());
		return map;
	}
	
}