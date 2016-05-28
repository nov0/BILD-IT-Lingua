package org.bildit.lingua.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.bildit.lingua.service.TicketService;
import org.bildit.lingua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TicketService ticketService;
	
	@RequestMapping("/ticket-all")
	@ResponseBody
	public Map<String, Object> getAllTickets(Principal principal) {
		Map<String, Object> map = new HashMap<>();
		map.put("ticketsList", ticketService.getAllTicketsByUsername(principal.getName()));
		return map;
	}
	
	@RequestMapping("/users")
	public String getAllUsers(Model model) {
		
		model.addAttribute("users", userService.getAll());
		
		
		return "users";
	}
	
}
