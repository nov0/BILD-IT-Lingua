package org.bildit.lingua.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Administrator controllers
 * 
 * @author Goran Arsenic
 *
 */

@Controller
public class AdminController {

	/**
	 * @author Bojan Aleksic
	 * @param searchQuery
	 * @param userCheckbox
	 * @param selectedBan
	 * @return
	 * note: this method is going to be moderated, by whoever is going to implement
	 * method in the Service layer for search for users...
	 */
	@RequestMapping("/user-search")
	public String searchForUser(@RequestParam("searchQuery") String searchQuery, 
			@RequestParam(value="username", required=false) String username,
			@RequestParam(value="firstName", required=false) String firstName,
			@RequestParam(value="lastName", required=false) String lastName,
			@RequestParam(value="selectedBan", required=false) String selectedBan) {
		System.out.println("Search query: " + searchQuery);
		System.out.println("Search by: " + username);
		System.out.println("Search by: " + firstName);
		System.out.println("Search by: " + lastName);
		System.out.println("Banned by: " + selectedBan);
		return "redirect:/";
	}
	
}
