package org.bildit.lingua.controllers;

import java.security.Principal;
import java.util.List;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.model.User;
import org.bildit.lingua.repository.TicketRepository;
import org.bildit.lingua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * @author Bojan Aleksic
	 * @param model
	 * @param principal
	 * @return
	 * Returns list of tickets by user ID
	 */
	@RequestMapping("/get-all-tickets")
	public String getAllTickets(Model model, Principal principal) {
		User user = userRepository.findUserByUsername(principal.getName());
		List<Ticket> all = ticketRepository.findAllByUserId(user.getId());
		model.addAttribute("all", all);
		return "home";
	}
	
}
