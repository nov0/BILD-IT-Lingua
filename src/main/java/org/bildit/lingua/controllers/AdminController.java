package org.bildit.lingua.controllers;

import java.util.List;

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
	private AdminService adminService;
	
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
		} else if("allUsers".equals(selectedBan)) {
			users = userService.searchUsersByAllBans(username, firstName, lastName);
		} else {
			users = userService.searchUsers(username, firstName, lastName);
		}
		
		model.addAttribute("users", users);
		
		return "forward:/";
	}
	
	/**
	 * @author Bojan Aleksic
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/ban-user-request")
	public String banUserRequest(@RequestParam("id") Long id, Model model) {
		User user = userService.getOne(id);
		model.addAttribute("user", user);
		model.addAttribute("newEntryBan", user.isAddingBan());
		model.addAttribute("loginBan", !user.isEnabled());
		model.addAttribute("votingBan", user.isVotingBan());
		return "fragments/ban-confirmation-modal-content";
	}
	
	/**
	 * @author Bojan Aleksic
	 * @param id
	 * @param entryBan
	 * @param loginBan
	 * @param votingBan
	 * @return
	 */
	@RequestMapping("/ban-submit")
	public String banSubmit(
			@RequestParam Long id,
			@RequestParam boolean entryBan, 
			@RequestParam boolean loginBan,
			@RequestParam boolean votingBan) {
		
		if(userService.getOne(id).isAddingBan() != entryBan) {
			adminService.newEntryBan(id);
		}
		if(!userService.getOne(id).isEnabled() != loginBan) {
			adminService.loginBan(id);
		}
		if(userService.getOne(id).isVotingBan() != votingBan) {
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
