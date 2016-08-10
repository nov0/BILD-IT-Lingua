package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.BaseUser;
import org.bildit.lingua.model.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


public interface UserService extends BaseService<User, Long> {

	boolean existByUsername(String username);
	User findUserByUsername(String username);
	String userRegistration(String repeatPassword, String domesticLanguage, String foreignLanguage, BaseUser baseUser, BindingResult result, Model model);
	void setForeignLanguageForUser(String username, String languageTitle);
	
	List<User> searchUsersByVotingBan(String username, String firstName, String lastName);
	List<User> searchUsersByAddingBan(String username, String firstName, String lastName);
	List<User> searchUsersByLoginBan(String username, String firstName, String lastName);
	List<User> searchUsers(String username, String firstName, String lastName);
	List<User> searchUsersByAllBans(String username, String firstName, String lastName);
	
}
