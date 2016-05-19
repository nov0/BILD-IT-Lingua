package org.bildit.lingua.service;

import org.bildit.lingua.model.Admin;
import org.bildit.lingua.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of AdminService interface
 * 
 * @author Goran Arsenic
 *
 */
@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminRepository adminRepository;

	@Override
	public void saveAdmin(Admin newAdmin) {
		adminRepository.save(newAdmin);
	}
	
	

}
