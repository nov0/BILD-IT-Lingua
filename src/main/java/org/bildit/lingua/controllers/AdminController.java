package org.bildit.lingua.controllers;

import org.bildit.lingua.model.User;
import org.bildit.lingua.service.UserService;
import org.bildit.lingua.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Administrator controllers
 * 
 * @author Goran Arsenic
 *
 */

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;

	@Autowired
	AdminService adminService;

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
	
	@RequestMapping("/ban-user-request")
	public String banUserRequest(@RequestParam("id") Long id, Model model) {
		User user = userService.getOne(id);
		model.addAttribute("user", user);
		model.addAttribute("newEntryBan", user.isAddingBan());
		model.addAttribute("loginBan", user.isLoginBan());
		model.addAttribute("voteBan", user.isVotingBan());
		return "fragments/ban-confirmation-modal-content";
	}
	
	@RequestMapping("/ban-submit")
	public String banSubmit(
			@RequestParam Long id,
			@RequestParam boolean entryBan, 
			@RequestParam boolean loginBan,
			@RequestParam boolean votingBan) {
		System.out.println("userID: " + id);
		System.out.println("entryBan: " + entryBan);
		System.out.println("loginBan: " + loginBan);
		System.out.println("votingaBan: " + votingBan);
		if(entryBan) {
			adminService.newEntryBan(id);
		}
		if(loginBan) {
			adminService.loginBan(loginBan, id);
			System.out.println("is user banned for login? " + adminService.loginBan(loginBan, id));
		}
		if(votingBan) {
			adminService.voteBan(id);
		}
		return "redirect:/";
	}
	
	/**
	 * Add new entry ban
	 * 
	 * @author Goran Arsenic
	 */
	@RequestMapping("/new-entry-ban")
	@ResponseBody
	public boolean addNewEntryBan(@RequestParam("userId") Long userId) {
		return adminService.newEntryBan(userId);
	}
	/**
	 * Login ban
	 * 
	 * @author Goran Arsenic
	 */
	@RequestMapping("/login-ban")
	@ResponseBody
	public boolean loginBan(@RequestParam("userId") Long userId, @RequestParam boolean loginBan) {
//		return adminService.loginBan(userId);
		return adminService.loginBan(loginBan, userId);
	}
	/**
	 * Vote ban
	 * 
	 * @author Goran Arsenic
	 */
	@RequestMapping("/vote-ban")
	@ResponseBody
	public boolean voteBan(@RequestParam("userId") Long userId) {
		return adminService.voteBan(userId);
	}
	
}
