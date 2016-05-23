package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.User;
import org.bildit.lingua.repository.BaseRepository;
import org.bildit.lingua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {


	@Autowired
	private UserRepository userRepository;

	public UserServiceImpl(BaseRepository<User, Long> baseRepository) {
		super(baseRepository);
		
	}

	@Override
	public List<User> getAll() {
		
		return userRepository.findAll();
	}
	
	

	
	
}
