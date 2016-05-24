package org.bildit.lingua.controllers;

import java.security.Principal;
import java.util.List;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	

	@RequestMapping(value = "/get-all-tickets", method = RequestMethod.POST)
	@ResponseBody
	public List<Ticket> getAllTickets(Principal principal) {
		return ticketService.getAllTicketsByUsername(principal.getName());
	}
	
	@RequestMapping(value = "/get-active-tickets", method = RequestMethod.POST)
	@ResponseBody
	public List<Ticket> getActiveTickets(Principal principal) {
		return ticketService.getActiveTickets(principal.getName());
	}
	
	@RequestMapping(value = "/get-deleted-tickets", method = RequestMethod.POST)
	@ResponseBody
	public List<Ticket> getDeletedTickets(Principal principal) {
		return ticketService.getDeletedTickets(principal.getName());
	}
	
	@RequestMapping(value = "/get-moderated-tickets", method = RequestMethod.POST)
	@ResponseBody
	public List<Ticket> getModeratedTickets(Principal principal) {
		return ticketService.getModeratedTickets(principal.getName());
	}
}
