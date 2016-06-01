package org.bildit.lingua.service;

import org.bildit.lingua.model.BaseUser;
import org.bildit.lingua.model.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


public interface UserService extends BaseService<User, Long> {

	boolean existByUsername(String username);
	User findUserByUsername(String username);
	String userRegistration(String repeatPassword, String domesticLanguage, BaseUser baseUser, BindingResult result, Model model);
	void setForeignLanguageForUser(String username, String languageTitle);
	
}
