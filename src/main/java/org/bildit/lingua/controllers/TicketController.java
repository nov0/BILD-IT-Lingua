package org.bildit.lingua.controllers;

import java.util.List;

import org.bildit.lingua.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TicketController {

	@RequestMapping(value = "/get-all-tickets", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllTickets() {
		return null;
	}
	
	
}
