package org.bildit.lingua.model;

import javax.persistence.Entity;

/**
 * Admin model
 * @author Mladen Todorovic
 * */
@Entity
public class Admin extends BaseUser {
	
	private static final long serialVersionUID = 1L;
	
	public Admin() {
		/** Empty default constructor */
	}
	
	/** Constructor with parameters */
	public Admin(BaseUser user) {
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setEmail(user.getEmail());
	}

}
