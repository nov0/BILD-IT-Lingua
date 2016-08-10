package org.bildit.lingua.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.model.User;
import org.bildit.lingua.service.LanguageService;
import org.bildit.lingua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Bojan Aleksic
 */
@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LanguageService languageService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String goToHome(Model model, Authentication auth, HttpSession session, Ticket ticket) {
		if(auth != null) {
			model.addAttribute("authority", auth.getAuthorities().toString());
			session.setAttribute("languages", languageService.getAllLanguages());
			if("[USER]".equals(auth.getAuthorities().toString())) {
				User user = userService.findUserByUsername(auth.getName());
				session.setAttribute("loggedUser", user);
				if(user.getForeignLanguage() != null) {
					session.setAttribute("foreignLanguage", user.getForeignLanguage().getLanguageTitle());
				}
				model.addAttribute("ticket", ticket);
			}
		}
		return "home";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/login")
	public String goToLogin() {
		return "login";
	}

}
