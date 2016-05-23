package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.Admin;
import org.bildit.lingua.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public List<Admin> getAll() {
		return adminRepository.findAll();
	}

	@Override
	public Admin getOne(Long id) {
		return adminRepository.getOne(id);
	}

}
