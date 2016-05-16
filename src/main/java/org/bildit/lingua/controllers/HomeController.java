package org.bildit.lingua.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bildit.lingua.model.User;
import org.bildit.lingua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String goToHome(Model model) {
		return "home";
	}
	
	/**
	 * @Author Bojan Aleksic
	 * @param model
	 * @return
	 * List all users. Modify or delete later.
	 */
	@RequestMapping("/get-users")
	public String getAllUsers(Model model) {
		List<User> users = userRepository.findAll();
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
		User user = userRepository.getOne(id);
		model.addAttribute("user", user);
		return "user-account";
	}
	
	/**
	 * @Author Bojan Aleksic
	 * @param request
	 * @param response
	 * @return
	 * redirect:/login?logout to implement later
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "home";
//		return "redirect:/login?logout";
	}
	
	@RequestMapping(value="/login")
	public String goToLogin() {
		return "login";
	}

}
