package org.bildit.lingua.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.bildit.lingua.common.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Ticket model
 * @author Mladen Todorovic
 * */
@Entity
@Table(name = "tickets")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class Ticket extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String category;
	
	private String textDomestic;
	
	private String textForeign;
	
	@Temporal(TemporalType.DATE)
	private Date deactivated;
	
	@Column(columnDefinition = "BIT", length = 1)
	private boolean edited;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Vote likes;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Vote dislikes;
	
	private String dateCreated;
	
	@OneToOne
	private Language learningLanguage;
	
	public Ticket() {
		/** Empty default constructor */
	}
	
	/** Getters and Setters */
	public Vote getLikes() {
		return likes;
	}

	public void setLikes(Vote likes) {
		this.likes = likes;
	}

	public Vote getDislikes() {
		return dislikes;
	}

	public void setDislikes(Vote dislikes) {
		this.dislikes = dislikes;
	}

	public Language getLearningLanguage() {
		return learningLanguage;
	}

	public void setLearningLanguage(Language learningLanguage) {
		this.learningLanguage = learningLanguage;
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
	
	public Date getDeactivated() {
		return deactivated;
	}

	public void setDeactivated(Date deactivated) {
		this.deactivated = deactivated;
	}

	public boolean isEdited() {
		return edited;
	}

	public void setEdited(boolean edited) {
		this.edited = edited;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
}
