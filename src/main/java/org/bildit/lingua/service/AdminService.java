package org.bildit.lingua.service;

import org.bildit.lingua.model.Admin;
import org.springframework.stereotype.Service;

/**
 * Implemented by AdminServiceImpl
 * 
 * @author Goran Arsenic
 *
 */
@Service
public interface AdminService {

	void saveAdmin(Admin newAdmin);
	
}
