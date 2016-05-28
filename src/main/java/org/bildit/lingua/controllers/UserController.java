package org.bildit.lingua.controllers;

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
	
	@SuppressWarnings("unused")
	@Autowired
	private TicketService ticketService;
	
	@RequestMapping("/users")
	public String getAllUsers(Model model) {
		model.addAttribute("users", userService.getAll());
		return "users";
	}
	
}
