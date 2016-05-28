package org.bildit.lingua.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.bildit.lingua.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TicketController {
	
	private static final String TICKETS = "ticketsList";
	
	@Autowired
	private TicketService ticketService;
	

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
}
