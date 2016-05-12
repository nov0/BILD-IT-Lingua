package org.bildit.lingua.controllers;



/**
 * @author NuLL
 * 
 *         Controller for registering and editing users information.
 *
 */
import javax.validation.Valid;

import org.bildit.lingua.model.Admin;
import org.bildit.lingua.model.BaseUser;
import org.bildit.lingua.model.User;
import org.bildit.lingua.repository.AdminRepository;
import org.bildit.lingua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@RequestMapping(value="/registration1", method=RequestMethod.GET)
	public String registerUser(BaseUser user, Model model) {
		model.addAttribute("user", user);
		return "registration-page";
	}
	
	/** Registering administrator */
	@RequestMapping(value="/register-admin", method=RequestMethod.POST)
	public String registerAdmin(BaseUser user, Model model) {
		Admin admin = new Admin(user);
		admin.setEnabled(true);
		admin.setAuthority("ADMIN");
		adminRepository.save(admin);
		return "home";
	}
	
	@RequestMapping("/registration")
	public String goToRegistration(Model model, User user) {
		model.addAttribute("user", user);
		return "registration";
	}
	
	@RequestMapping("/registration-check")
	public String goToRegistrationFail(
			@RequestParam("repeatpassword") String repeatPassword,
			@RequestParam("repeatemail") String repeatEmail, 
			@Valid User user,
			BindingResult result,
			Model model) {

		if (!user.getPassword().equals(repeatPassword)) {
			model.addAttribute("repassword", true);
			return "registration";
		}
		
		if (!user.getEmail().equals(repeatEmail)) {
			return "registration";
		}

		if (result.hasErrors()) {
			return "registration";
		} else {
			userRepository.save(user);
		}

		return "home";
	}
}
