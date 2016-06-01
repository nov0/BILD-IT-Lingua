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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * 
 * User model
 * 
 * @author Mladen Todorovic
 * 
 * */

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class User extends BaseUser {
	
	private static final long serialVersionUID = 1L;
	
	@Column(columnDefinition = "BIT", length = 1)
	private boolean votingBan = false;
	
	@Column(columnDefinition = "BIT", length = 1)
	private boolean addingBan = false;
	
	@Column(columnDefinition = "BIT", length = 1)
	private boolean loginBan = false;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="user_tickets", 
	   joinColumns=@JoinColumn(name="user_id"),
	   inverseJoinColumns=@JoinColumn(name="ticket_id"))
	List<Ticket> tickets = new ArrayList<>();
	
	@OneToOne
	private Language domesticLanguage;
	
	@OneToOne
	private Language foreignLanguage;
	
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
	
	public Language getDomesticLanguage() {
		return domesticLanguage;
	}

	public void setDomesticLanguage(Language domesticLanguage) {
		this.domesticLanguage = domesticLanguage;
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

	public Language getForeignLanguage() {
		return foreignLanguage;
	}

	public void setForeignLanguage(Language foreignLanguage) {
		this.foreignLanguage = foreignLanguage;
	}

}
