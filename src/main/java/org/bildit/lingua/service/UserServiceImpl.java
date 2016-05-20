package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.User;
import org.bildit.lingua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of UserService interface
 * 
 * @author Goran Arsenic
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> findeAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findUser(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public void saveUser(User newUser) {
		userRepository.save(newUser);		
	}
	
	@Override
	public boolean isUsernameExist(String username) {
		return userRepository.existByUsername(username);
	}
	
}
