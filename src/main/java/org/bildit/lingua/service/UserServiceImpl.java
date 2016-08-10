package org.bildit.lingua.service;

import java.util.ArrayList;
import java.util.List;

import org.bildit.lingua.model.BaseUser;
import org.bildit.lingua.model.Language;
import org.bildit.lingua.model.User;
import org.bildit.lingua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class UserServiceImpl implements UserService {
	
	private static final String REGISTRATION = "registration";
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LanguageService languageServices;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
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
	
	/**
	 * @author Mladen Todorovic
	 * Method: find user by username
	 * */
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
			return REGISTRATION;
		}
		
		if(userRepository.existByUsername(baseUser.getUsername())) {
			model.addAttribute("usernameExist", true);
			model.addAttribute("allLanguages", languageServices.getAll());
			return REGISTRATION;
		}

		if (result.hasErrors()) {
			model.addAttribute("allLanguages", languageServices.getAll());
			return REGISTRATION;
		} else {
			User user = new User(baseUser);
				
			/* setting first letter to upper case in first name and last name */
			String firstName = user.getFirstName();
			firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
			String lastName = user.getLastName();
			lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			// setting username to lower case
			user.setUsername(user.getUsername().toLowerCase());
			
			if(user.getPassword().length() < 40) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			}

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
		userRepository.flush();
	}
	/**
	 * @author Mladen Todorovic
	 * Method: return list of users found by username or firstName or lastName and votingBan set to true
	 * */
	@Override
	public List<User> searchUsersByVotingBan(String username, String firstName, String lastName) {
		
		List<User> users = new ArrayList<>();
		
		if (!username.isEmpty()) {
			users = userRepository.findAllByVotingBanAndUsernameOrderByAsc(username, new PageRequest(0, 10));
		} else {
			if (!firstName.isEmpty() && !lastName.isEmpty()) {
				users = userRepository.findAllByVotingBanAndFirstNameAndLastNameOrderByAsc(firstName, lastName, new PageRequest(0, 10));
			} else if (!firstName.isEmpty() && lastName.isEmpty()) {
				users = userRepository.findAllByVotingBanAndFirstNameOrderByAsc(firstName, new PageRequest(0, 10));
			} else if (firstName.isEmpty() && !lastName.isEmpty()) {
				users = userRepository.findAllByVotingBanAndLastNameOrderByAsc(lastName, new PageRequest(0, 10));
			} else {
				users = null;
			}
		}
		return users;
	}
	/**
	 * @author Mladen Todorovic
	 * Method: return list of users found by username or firstName or lastName and addingBan set to true
	 * */
	@Override
	public List<User> searchUsersByAddingBan(String username, String firstName, String lastName) {
		
		List<User> users = new ArrayList<>();
		
		if (!username.isEmpty()) {
			users = userRepository.findAllByAddingBanAndUsernameOrderByAsc(username, new PageRequest(0, 10));
		} else {
			if (!firstName.isEmpty() && !lastName.isEmpty()) {
				users = userRepository.findAllByAddingBanAndFirstNameAndLastNameOrderByAsc(firstName, lastName, new PageRequest(0, 10));
			} else if (!firstName.isEmpty() && lastName.isEmpty()) {
				users = userRepository.findAllByAddingBanAndFirstNameOrderByAsc(firstName, new PageRequest(0, 10));
			} else if (firstName.isEmpty() && !lastName.isEmpty()) {
				users = userRepository.findAllByAddingBanAndLastNameOrderByAsc(lastName, new PageRequest(0, 10));
			} else {
				users = null;
			}
		}
		return users;
	}
	/**
	 * @author Mladen Todorovic
	 * Method: return list of users found by username or firstName or lastName and enabled set to false
	 * */
	@Override
	public List<User> searchUsersByLoginBan(String username, String firstName, String lastName) {
		
		List<User> users = new ArrayList<>();
		
		if (!username.isEmpty()) {
			users = userRepository.findAllByLoginBanAndUsernameOrderByAsc(username, new PageRequest(0, 10));
		} else {
			if (!firstName.isEmpty() && !lastName.isEmpty()) {
				users = userRepository.findAllByLoginBanAndFirstNameAndLastNameOrderByAsc(firstName, lastName, new PageRequest(0, 10));
			} else if (!firstName.isEmpty() && lastName.isEmpty()) {
				users = userRepository.findAllByLoginBanAndFirstNameOrderByAsc(firstName, new PageRequest(0, 10));
			} else if (firstName.isEmpty() && !lastName.isEmpty()) {
				users = userRepository.findAllByLoginBanAndLastNameOrderByAsc(lastName, new PageRequest(0, 10));
			} else {
				users = null;
			}
		}
		return users;
	}
	/**
	 * @author Mladen Todorovic
	 * Method: return list of users found by username or firstName or lastName
	 * */
	@Override
	public List<User> searchUsers(String username, String firstName, String lastName) {
		
		List<User> users = new ArrayList<>();
		
		if (!username.isEmpty()) {
			users = userRepository.findAllByUsernameOrderByAsc(username, new PageRequest(0, 10));
		} else {
			if (!firstName.isEmpty() && !lastName.isEmpty()) {
				users = userRepository.findAllByFirstNameAndLastNameOrderByAsc(firstName, lastName, new PageRequest(0, 10));
			} else if (!firstName.isEmpty() && lastName.isEmpty()) {
				users = userRepository.findAllByFirstNameOrderByAsc(firstName, new PageRequest(0, 10));
			} else if (firstName.isEmpty() && !lastName.isEmpty()) {
				users = userRepository.findAllByLastNameOrderByAsc(lastName, new PageRequest(0, 10));
			} else {
				users = null;
			}
		}
		return users;
	}
	/**
	 * @author Mladen Todorovic
	 * Method: return list of users found by username or firstName or lastName
	 *         and votingBan set to true or addingBan set to true or enabled set to false
	 * */
	@Override
	public List<User> searchUsersByAllBans(String username, String firstName, String lastName) {
		
		List<User> users = new ArrayList<>();
		
		if (!username.isEmpty()) {
			users = userRepository.findByAllBansAndUsernameOrderByAsc(username, new PageRequest(0, 10));
		} else {
			if (!firstName.isEmpty() && !lastName.isEmpty()) {
				users = userRepository.findByAllBansAndFirstNameAndLastNameOrderByAsc(firstName, lastName, new PageRequest(0, 10));
			} else if (!firstName.isEmpty() && lastName.isEmpty()) {
				users = userRepository.findByAllBansAndFirstNameOrderByAsc(firstName, new PageRequest(0, 10));
			} else if (firstName.isEmpty() && !lastName.isEmpty()) {
				users = userRepository.findByAllBansAndLastNameOrderByAsc(lastName, new PageRequest(0, 10));
			} else {
				users = null;
			}
		}
		return users;
	}
	
}
