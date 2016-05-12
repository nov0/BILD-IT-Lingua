package org.bildit.lingua.repository;

import org.bildit.lingua.model.Admin;

public interface AdminRepository extends BaseRepository<Admin, Long> {
	
	@SuppressWarnings("unchecked")
	Admin save(Admin entity);
	
}
