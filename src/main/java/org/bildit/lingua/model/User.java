package org.bildit.lingua.model;

import java.time.LocalDateTime;
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
 * @author Mladen Todorovic
 * @class User
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
	
	private LocalDateTime dateOfVotingBan;
	
	private LocalDateTime dateOfAddingBan;
	
	private LocalDateTime dateOfLoginBan;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	@JoinTable(name="user_tickets", 
	   joinColumns=@JoinColumn(name="user_id"),
	   inverseJoinColumns=@JoinColumn(name="ticket_id"))
	private List<Ticket> tickets = new ArrayList<>();
	
	@OneToOne
	private Language domesticLanguage;
	
	@OneToOne
	private Language foreignLanguage;
	
	public User() {
		/** Empty default constructor */
	}
	
	/** Constructor with parameters */
	public User(BaseUser user) {
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setEmail(user.getEmail());
	}
	
	/** Constructor with parameters */
	public User(boolean votingBan, boolean addingBan, boolean loginBan) {
		this.votingBan = votingBan;
		this.addingBan = addingBan;
		this.loginBan = loginBan;
	}
	/** Method: return total number of all user's tickets */
	public int sumOfAllUserTickets() {
		return this.getTickets().size();
	}
	
	/** Method: return sum of all user's tikets votes (likes) */
	public int sumOfAllUserTicketsLikes() {
		int sum = 0;
		for (Ticket ticket: this.getTickets()) {
			sum += ticket.getTicketVotes().getLikes();
		}
		return sum;
	}
	/** Method: return sum of all user's tikets votes (dislikes) */
	public int sumOfAllUserTicketsDislikes() {
		int sum = 0;
		for (Ticket ticket: this.getTickets()) {
			sum += ticket.getTicketVotes().getDislikes();
		}
		return sum;
	}
	
	/** Getters and Setters */
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

	public LocalDateTime getDateOfVotingBan() {
		return dateOfVotingBan;
	}

	public void setDateOfVotingBan(LocalDateTime dateOfVotingBan) {
		this.dateOfVotingBan = dateOfVotingBan;
	}

	public LocalDateTime getDateOfAddingBan() {
		return dateOfAddingBan;
	}

	public void setDateOfAddingBan(LocalDateTime dateOfAddingBan) {
		this.dateOfAddingBan = dateOfAddingBan;
	}

	public LocalDateTime getDateOfLoginBan() {
		return dateOfLoginBan;
	}

	public void setDateOfLoginBan(LocalDateTime dateOfLoginBan) {
		this.dateOfLoginBan = dateOfLoginBan;
	}
	
}
