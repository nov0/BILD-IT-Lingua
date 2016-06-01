package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.Admin;
import org.bildit.lingua.model.BaseUser;
import org.bildit.lingua.repository.AdminRepository;
import org.bildit.lingua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Admin> getAll() {
		return adminRepository.findAll();
	}

	@Override
	public Admin getOne(Long id) {
		return adminRepository.getOne(id);
	}

	@Override
	public void delete(Long id) {
		adminRepository.delete(id);
	}
	/**
	 * 
	 * @author Mladen Todorovic
	 * 
	 * Method for registering administrator 
	 * 
	 * */
	public String registerAdmin(
			String repeatPassword,
			BaseUser baseUser,
			BindingResult result,
			Model model) {

		if (!baseUser.getPassword().equals(repeatPassword)) {
			model.addAttribute("repassword", true);
			return "registration-page";
		}
		
		if(userRepository.existByUsername(baseUser.getUsername())) {
			model.addAttribute("usernameExist", true);
			return "registration-page";
		}

		if (result.hasErrors()) {
			return "registration-page";
		} else {
			Admin admin = new Admin(baseUser);
			
			String firstName = admin.getFirstName();
			firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
			String lastName = admin.getLastName();
			lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
			
			admin.setFirstName(firstName);
			admin.setLastName(lastName);
			
			admin.setUsername(admin.getUsername().toLowerCase());
			
			admin.setEnabled(true);
			admin.setAuthority("ADMIN");
			adminRepository.save(admin);
		}
		
		return "redirect:/?adminreg";
	}
}