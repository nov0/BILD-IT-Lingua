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
 * @author Mladen Todorovic
 * @class Vote
 */
@Entity
@Table(name = "votes")
public class Vote extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private int likes;
	private int dislikes;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="votes_users", 
	   joinColumns=@JoinColumn(name="vote_id"),
	   inverseJoinColumns=@JoinColumn(name="user_id"))
	private Set<User> votedUsers = new HashSet<>();
	
	@OneToOne
	private Ticket ticket;
	
	public Vote() {
		/** Empty default constructor */
	}
	
	/** Constructor with parameters */
	public Vote(int likes, int dislikes) {
		this.likes = likes;
		this.dislikes = dislikes;
	}
	
	/** Getters and Setters */
	public Set<User> getVotedUsers() {
		return votedUsers;
	}
	
	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	/** Method for incrementing likes */
	public void incrementLikes() {
		likes++;
	}
	
	/** Method for incrementing dislikes */
	public void incrementDislikes() {
		dislikes++;
	}
	
}
