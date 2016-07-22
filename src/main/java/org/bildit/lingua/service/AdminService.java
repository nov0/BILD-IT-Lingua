package org.bildit.lingua.service;

import org.bildit.lingua.model.Admin;
import org.bildit.lingua.model.BaseUser;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface AdminService extends BaseService<Admin, Long> {

	public String registerAdmin(String repeatPassword, BaseUser baseUser, BindingResult result,	Model model);
	public boolean newEntryBan(long userId);
	public boolean loginBan(Long userId);
	public boolean voteBan(Long userId);
	
//	public boolean newEntryBan(boolean entryBan, Long id);
//	public boolean loginBan(boolean loginBan, Long id);
//	public boolean voteBan(boolean votingBan, Long id);
	
}
