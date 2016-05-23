package org.bildit.lingua.controllers;

import java.security.Principal;
import java.util.List;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.model.User;
import org.bildit.lingua.repository.TicketRepository;
import org.bildit.lingua.repository.UserRepository;
import org.bildit.lingua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * @Author Bojan Aleksic
	 * @param model
	 * @return
	 * List all users. Modify or delete later.
	 */
	@RequestMapping("/get-users")
	public String getAllUsers(Model model) {
		List<User> users = userService.findeAllUsers();
		model.addAttribute("users", users);
		return "users";
	}
	
	/**
	 * @Author Bojan Aleksic
	 * @param id
	 * @param model
	 * @return
	 * Method for testing user-account purposes.
	 * Modify or delete later 
	 */
	@RequestMapping("/user-account")
	public String userAccount(@RequestParam("id") Long id, Model model) {
		User user = userService.findUser(id);
		model.addAttribute("user", user);
		return "user-account";
	}
	
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
