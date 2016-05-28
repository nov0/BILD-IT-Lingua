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
import org.bildit.lingua.repository.AdminRepository;
import org.bildit.lingua.repository.UserRepository;
import org.bildit.lingua.service.LanguageServiceImpl;
import org.bildit.lingua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegistrationController {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LanguageServiceImpl languageServices;
	
	/**
	 * 
	 * @author Mladen Todorovic
	 * 
	 * */
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerUser(BaseUser baseUser, Model model) {
		model.addAttribute("baseUser", baseUser);
		return "registration-page";
	}
	/**
	 * 
	 * @author Mladen Todorovic
	 * 
	 * Method for registering administrator 
	 * 
	 * */
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
	
	/**
	 * 
	 * @author Novislav Sekulic
	 * 
	 * */
	@RequestMapping("/registration")
	public String goToRegistration(Model model, BaseUser baseUser) {
		model.addAttribute("baseUser", baseUser);
		model.addAttribute("allLanguages", languageServices.getAll());
		return "registration";
	}
	

	/**
	 * 
	 * @author Novislav Sekulic
	 * 
	 * Method for validating user fields.
	 * 
	 * @param repeatPassword
	 * @param baseUser
	 * @param result
	 * @param model
	 * 
	 */
	@RequestMapping("/registration-check")
	public String goToRegistrationFail(
			@RequestParam("repeatpassword") String repeatPassword,
			@RequestParam("domestic") String domesticLanguage,
			@Valid BaseUser baseUser,
			BindingResult result,
			Model model) {

		return userService.userRegistration(repeatPassword, domesticLanguage, baseUser, result, model);
	}
	
	/**
	 * @author Novislav Sekulic
	 * @param username
	 * @return
	 * Method for checking is username exist in database.
	 */
	@RequestMapping(value="/existusername", method=RequestMethod.GET)
	@ResponseBody
	public boolean isUsernameExist(@RequestParam(name="username") String username) {
		return userService.existByUsername(username);
	}

}
