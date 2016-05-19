package org.bildit.lingua.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.bildit.lingua.common.BaseEntity;

/**
 * 
 * Ticket model
 * 
 * @author Mladen Todorovic
 * 
 * */

@Entity
@Table(name = "ticket")
public class Ticket extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String textDomestic;
	private String textForeign;
	
	@Column(columnDefinition = "BIT", length = 1)
	private boolean active = false;
	@Column(columnDefinition = "BIT", length = 1)
	private boolean deleted = false;
	@Column(columnDefinition = "BIT", length = 1)
	private boolean edited = false;
	
	@OneToOne
	private User user;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="TICKET_VOTE_UP", 
	   		   joinColumns=@JoinColumn(name="ticket_id"),
	   		   inverseJoinColumns=@JoinColumn(name="vote_id"))
	List<Vote> votesUp = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="TICKET_VOTE_DOWN", 
	   		   joinColumns=@JoinColumn(name="ticket_id"),
	   		   inverseJoinColumns=@JoinColumn(name="vote_id"))
	List<Vote> votesDown = new ArrayList<>();
	
	@Temporal(TemporalType.DATE)
	private Date dateCreated;
	
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isEdited() {
		return edited;
	}

	public void setEdited(boolean edited) {
		this.edited = edited;
	}

	public String getTextDomestic() {
		return textDomestic;
	}

	public void setTextDomestic(String textDomestic) {
		this.textDomestic = textDomestic;
	}

	public String getTextForeign() {
		return textForeign;
	}

	public void setTextForeign(String textForeign) {
		this.textForeign = textForeign;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public List<Vote> getVotesUp() {
		return votesUp;
	}

	public List<Vote> getVotesDown() {
		return votesDown;
	}
	
}
