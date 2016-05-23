package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.User;
import org.bildit.lingua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
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

	
	
}
