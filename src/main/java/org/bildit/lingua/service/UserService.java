package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.User;
import org.springframework.stereotype.Service;

/**
 * Implemented by UserServiceImpl
 * 
 * @author Goran Arsenic
 *
 */
@Service
public interface UserService {

	List<User> findeAllUsers();
	User getUser(Long id);
	
}
