package org.bildit.lingua.controllers;

import org.bildit.lingua.model.Admin;
import org.bildit.lingua.model.BaseUser;
import org.bildit.lingua.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {
	
	@Autowired
	private AdminRepository adminRepository;
	
	
	@RequestMapping(value="/registration", method=RequestMethod.GET)
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
	
}
