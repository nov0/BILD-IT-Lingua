package org.bildit.lingua.service;

import org.bildit.lingua.model.User;


public interface UserService extends BaseService<User, Long> {

	boolean existByUsername(String username);
	
}
