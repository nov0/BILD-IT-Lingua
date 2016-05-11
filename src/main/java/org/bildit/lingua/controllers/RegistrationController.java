package org.bildit.lingua.controllers;

import javax.validation.Valid;

import org.bildit.lingua.model.User;
import org.bildit.lingua.model.UserDto;
import org.bildit.lingua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/registration")
	public String goToRegistration(Model model, UserDto userDto) {
		model.addAttribute("userDto", userDto);
		return "registration";
	}
	
	@RequestMapping("/registration-form")
	public String goToRegistrationFail(@Valid UserDto userDto, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "registration";
		} else {
			User user = new User();
			
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setUsername(userDto.getUsername());
			user.setPassword(userDto.getPassword());
			user.setEmail(userDto.getEmail());
			user.setEnabled(true);
			
			userRepository.save(user);
		}
		
		return "home";
	}

}
