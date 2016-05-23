package org.bildit.lingua.controllers;



/**
 * @author Novislav Sekulic, Mladen Todorovic
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
	AdminRepository adminRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerUser(BaseUser baseUser, Model model) {
		model.addAttribute("baseUser", baseUser);
		return "registration-page";
	}
	
	/** Registering administrator */
	@RequestMapping(value="/register-admin", method=RequestMethod.POST)
	public String registerAdmin(
			@RequestParam("repeatpassword") String repeatPassword,
			@Valid BaseUser baseUser,
			BindingResult result,
			Model model) {

		if (!baseUser.getPassword().equals(repeatPassword)) {
			model.addAttribute("repassword", true);
			return "registration-page";
		}

		if (result.hasErrors()) {
			return "registration-page";
		} else {
			Admin admin = new Admin(baseUser);
			admin.setEnabled(true);
			admin.setAuthority("ADMIN");
			adminRepository.save(admin);
		}
		
		return "home";
	}
	
	@RequestMapping("/registration")
	public String goToRegistration(Model model, BaseUser baseUser) {
		model.addAttribute("baseUser", baseUser);
		return "registration";
	}
	

	/**
	 * Method for validating user fields.
	 * 
	 * @param repeatPassword
	 * @param baseUser
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping("/registration-check")
	public String goToRegistrationFail(
			@RequestParam("repeatpassword") String repeatPassword,
			@Valid BaseUser baseUser,
			BindingResult result,
			Model model) {

		if (!baseUser.getPassword().equals(repeatPassword)) {
			model.addAttribute("repassword", true);
			return "registration";
		}

		if (result.hasErrors()) {
			return "registration";
		} else {
			User user = new User(baseUser);
				
			/* setting first letter to upper case in first name and last name */
			String firstName = user.getFirstName();
			firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
			String lastName = user.getLastName();
			lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			// setting username to lower case
			user.setUsername(user.getUsername().toLowerCase());
			
			user.setEnabled(true);
			user.setDomesticLanguage(null);
			user.setAddingBan(false);
			user.setLoginBan(false);
			user.setVotingBan(false);
			user.setAuthority("USER");
			userRepository.save(user);
		}

		return "home";
	}
}
