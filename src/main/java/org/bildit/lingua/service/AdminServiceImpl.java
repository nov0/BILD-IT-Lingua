package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.Admin;
import org.bildit.lingua.model.BaseUser;
import org.bildit.lingua.model.User;
import org.bildit.lingua.repository.AdminRepository;
import org.bildit.lingua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
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
			
			if(admin.getPassword().length() < 40) {
				admin.setPassword(passwordEncoder.encode(admin.getPassword()));
			}
			
			admin.setEnabled(true);
			admin.setAuthority("ADMIN");
			adminRepository.save(admin);
		}
		
		return "redirect:/?adminreg";
	}
	
	/**
	 * @author Goran Arsenic
	 */
	@Override
	public boolean newEntryBan(long userId) {
		User user = userRepository.findOne(userId);
		user.setAddingBan(!user.isAddingBan());
		userRepository.saveAndFlush(user);
		return user.isAddingBan();
	}
	/**
	 * @author Goran Arsenic
	 */
	@Override
	public boolean loginBan(Long userId) {
		User user = userRepository.findOne(userId);
		user.setEnabled(!user.isEnabled());
		userRepository.saveAndFlush(user);
		return user.isEnabled();
	}
	/**
	 * @author Goran Arsenic
	 */
	@Override
	public boolean voteBan(Long userId) {
		User user = userRepository.findOne(userId);
		user.setVotingBan(!user.isVotingBan());
		userRepository.saveAndFlush(user);
		return user.isVotingBan();
	}
	
}
