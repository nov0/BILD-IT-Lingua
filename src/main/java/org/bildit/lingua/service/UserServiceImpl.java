package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.BaseUser;
import org.bildit.lingua.model.Language;
import org.bildit.lingua.model.User;
import org.bildit.lingua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LanguageService languageServices;
	
	
	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User getOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public boolean existByUsername(String username) {
		return userRepository.existByUsername(username);
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
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
	@Override
	public String userRegistration(String repeatPassword, String domesticLanguage, String foreignLanguage, BaseUser baseUser,
			BindingResult result, Model model) {
		if (!baseUser.getPassword().equals(repeatPassword)) {
			model.addAttribute("repassword", true);
			model.addAttribute("allLanguages", languageServices.getAll());
			return "registration";
		}
		
		if(userRepository.existByUsername(baseUser.getUsername())) {
			model.addAttribute("usernameExist", true);
			model.addAttribute("allLanguages", languageServices.getAll());
			return "registration";
		}

		if (result.hasErrors()) {
			model.addAttribute("allLanguages", languageServices.getAll());
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

			user.setDomesticLanguage(languageServices.getOneByLanguageTitle(domesticLanguage));
			user.setForeignLanguage(languageServices.getOneByLanguageTitle(foreignLanguage));
			
			user.setEnabled(true);
			user.setLoginBan(false);
			user.setAddingBan(false);
			user.setVotingBan(false);
			user.setAuthority("USER");
			userRepository.save(user);
		}

		return "redirect:/?register";
	}

	/**
	 * @author Bojan Aleksic
	 * This method saves user's foreign (learning) language
	 */
	@Override
	public void setForeignLanguageForUser(String username, String languageTitle) {
		User user = userRepository.findUserByUsername(username);
		Language foreignLanguage = languageServices.getOneByLanguageTitle(languageTitle);
		user.setForeignLanguage(foreignLanguage);
		userRepository.updateForeignLanguageForUser(foreignLanguage, user.getId());
	}

	
	
}
