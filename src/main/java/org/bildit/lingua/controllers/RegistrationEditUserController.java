package org.bildit.lingua.controllers;

import javax.validation.Valid;

import org.bildit.lingua.model.User;
import org.bildit.lingua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author NuLL
 * 
 * Controller for registering and editing users information.
 *
 */
@Controller
public class RegistrationEditUserController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/registration")
	public String goToRegistration(Model model, User user) {
		model.addAttribute("user", user);
		return "registration";
	}
	
	@RequestMapping("/registration-form")
	public String goToRegistrationFail(@Valid User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "registration";
		} else {
			userRepository.save(user);
		}
		
		return "home";
	}

}
