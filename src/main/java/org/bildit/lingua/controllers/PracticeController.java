package org.bildit.lingua.controllers;

import java.security.Principal;
import java.util.List;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.service.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PracticeController {
	
	@Autowired
	PracticeService practiceService;
	
	//TODO: Now is just for test form submit, must be changed
	@RequestMapping(value="/practice", method=RequestMethod.POST)
	public String startPractice(
			@RequestParam("from") String from,
			@RequestParam("category") String category,
			@RequestParam("speed") String speed,
			@RequestParam("order") String order,
			Principal principal) {
		// get list of tickets for practice from database
		List<Ticket> ticketsForPractice = practiceService.getTicketsForPractice(from, category, principal.getName());
		
		
		/** this is crap for testing */
		for(Ticket t : ticketsForPractice) {
			System.out.println("user:" + t.getUser().getId() + "ticket id: " + t.getId() + ", language: " + t.getLearningLanguage().getLanguageTitle());
		}
		if(ticketsForPractice.isEmpty()) {
			System.out.println("*********** NEMA NIŠTA ***********");
		}
		/******************************/
		return "error";
	}
}
