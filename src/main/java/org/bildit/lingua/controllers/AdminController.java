package org.bildit.lingua.controllers;

import java.util.List;

import org.bildit.lingua.model.User;
import org.bildit.lingua.service.AdminService;
import org.bildit.lingua.service.UserService;
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
	AdminService adminService;
	
	@Autowired
	UserService userService;

	/**
	 * @author Bojan Aleksic
	 * @edit Mladen Todorovic
	 * @param username
	 * @param firstName
	 * @param lastName
	 * @param selectedBan
	 * @return
	 */
	@RequestMapping("/user-search")
	public String searchUsers(Model model,
			@RequestParam(value="username", required=false) String username,
			@RequestParam(value="firstName", required=false) String firstName,
			@RequestParam(value="lastName", required=false) String lastName,
			@RequestParam(value="selectedBan", required=false) String selectedBan) {
		
		List<User> users = null;
		
		if ("bannedVoting".equals(selectedBan)) {
			users = userService.searchUsersByVotingBan(username, firstName, lastName);
		} else if ("bannedAdding".equals(selectedBan)) {
			users = userService.searchUsersByAddingBan(username, firstName, lastName);
		} else if ("bannedLogin".equals(selectedBan)) {
			users = userService.searchUsersByLoginBan(username, firstName, lastName);
		} else {
			users = userService.searchUsers(username, firstName, lastName);
		}
		
		System.out.println(users.isEmpty());
		System.out.println(users.size());
		
//		for (User user: users) {
//			System.out.println();
//		}
		
		model.addAttribute("users", users);
		
		return "forward:/";
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
	public boolean loginBan(@RequestParam("userId") Long userId) {
		return adminService.loginBan(userId);
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
