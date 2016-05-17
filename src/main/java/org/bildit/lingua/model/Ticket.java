package org.bildit.lingua.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.bildit.lingua.common.BaseEntity;

@Entity
@Table(name = "ticket")
public class Ticket extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String textDomestic;
	
	private String textForeign;
	
	@OneToOne
	private User user;
	
	@OneToMany
	List<Vote> votesUp = new Arraylist<>();
	@OneToMany
	List<Vote> votesDown = new Arraylist<>();
	
	@Temporal(TemporalType.DATE)
	private Date dateCreated;

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
