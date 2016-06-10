package org.bildit.lingua.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.bildit.lingua.common.BaseEntity;

/**
 * @author Novislav Sekulic, edit: Mladen Todorovic
 */
@Entity
@Table(name = "votes")
public class Vote extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private int voteValue;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="votes_users", 
	   joinColumns=@JoinColumn(name="vote_id"),
	   inverseJoinColumns=@JoinColumn(name="user_id"))
	private Set<User> votedUsers = new HashSet<>();
	
	@OneToOne
	private Ticket ticket;
	
	public Vote() {
		/** Empty default constructor */
	}
	
	/** Constructor with parameter */
	public Vote(int voteValue) {
		this.voteValue = voteValue;
	}
	
	/** Getters and Setters */
	public void setVotedUsers(Set<User> votedUsers) {
		this.votedUsers = votedUsers;
	}

	public Set<User> getVotedUsers() {
		return votedUsers;
	}

	public int getVoteValue() {
		return voteValue;
	}

	public void setVoteValue(int voteValue) {
		this.voteValue = voteValue;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	/** Method for incrementing voteValue */
	public void incrementVoteValue() {
		voteValue++;
	}
	
}
