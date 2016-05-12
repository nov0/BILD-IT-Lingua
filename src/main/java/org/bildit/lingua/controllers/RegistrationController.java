package org.bildit.lingua.controllers;

import javax.validation.Valid;

import org.bildit.lingua.model.User;
import org.bildit.lingua.model.UserDto;
import org.bildit.lingua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author NuLL
 * 
 *         Controller for registering and editing users information.
 *
 */
@Controller
public class RegistrationController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/registration")
	public String goToRegistration(Model model, UserDto userDto) {
		model.addAttribute("userDto", userDto);
		return "registration";
	}

	@RequestMapping("/registration-check")
	public String goToRegistrationFail(
			@RequestParam("repeatpassword") String repeatPassword,
			@RequestParam("repeatemail") String repeatEmail, 
			@Valid UserDto userDto,
			BindingResult result,
			Model model) {

		if (!userDto.getPassword().equals(repeatPassword)) {
			model.addAttribute("repassword", true);
			return "registration";
		}
		
		if (!userDto.getEmail().equals(repeatEmail)) {
			System.out.println(userDto.getEmail());
			return "registration";
		}

		if (result.hasErrors()) {
			return "registration";
		} else {

			User user = new User();

			user.setId(userDto.getId());
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setEmail(userDto.getEmail());
			user.setUsername(userDto.getUsername());
			user.setPassword(userDto.getPassword());
			user.setAuthority(userDto.getAuthority());
			user.setEnabled(userDto.isEnabled());
			// saving or updating user
			userRepository.save(user);
		}

		return "home";
	}

}
