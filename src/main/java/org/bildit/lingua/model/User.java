package org.bildit.lingua.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 
 * User model
 * 
 * @author Mladen Todorovic
 * 
 * */

@Entity
public class User extends BaseUser {
	
	private static final long serialVersionUID = 1L;
	
	@Column(columnDefinition = "BIT", length = 1)
	private boolean votingBan = false;
	
	@Column(columnDefinition = "BIT", length = 1)
	private boolean addingBan = false;
	
	@Column(columnDefinition = "BIT", length = 1)
	private boolean loginBan = false;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="USER_TICKETS", 
	   joinColumns=@JoinColumn(name="user_id"),
	   inverseJoinColumns=@JoinColumn(name="ticket_id"))
	List<Ticket> tickets = new ArrayList<>();
	
	@OneToOne
	private Language defaultLanguage;
	
	public User() {
		/** Empty default constructor */
	}
	
	/** Constructor with params */
	public User(BaseUser user) {
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setEmail(user.getEmail());
	}
	
	/** Constructor with params */
	public User(boolean votingBan, boolean addingBan, boolean loginBan) {
		this.votingBan = votingBan;
		this.addingBan = addingBan;
		this.loginBan = loginBan;
	}
	
	public Language getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(Language defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public boolean isVotingBan() {
		return votingBan;
	}

	public void setVotingBan(boolean votingBan) {
		this.votingBan = votingBan;
	}

	public boolean isAddingBan() {
		return addingBan;
	}

	public void setAddingBan(boolean addingBan) {
		this.addingBan = addingBan;
	}

	public boolean isLoginBan() {
		return loginBan;
	}

	public void setLoginBan(boolean loginBan) {
		this.loginBan = loginBan;
	}

}
