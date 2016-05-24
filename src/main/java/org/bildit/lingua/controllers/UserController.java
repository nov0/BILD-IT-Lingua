package org.bildit.lingua.controllers;

import java.security.Principal;

import org.bildit.lingua.service.TicketService;
import org.bildit.lingua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TicketService ticketService;
	
	/**
	 * @author Bojan Aleksic
	 * @param model
	 * @param principal
	 * @return
	 * Returns list of tickets by user username
	 */
	@RequestMapping("/get-all-tickets")
	public String getAllTickets(Model model, Principal principal) {
		model.addAttribute("allTickets", ticketService.getAllTicketsByUsername(principal.getName()));
		return "home";
	}
	
	@RequestMapping("/users")
	public String getAllUsers(Model model) {
		
		model.addAttribute("users", userService.getAll());
		
		
		return "users";
	}
	
}
