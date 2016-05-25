package org.bildit.lingua.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.bildit.lingua.common.BaseEntity;

/**
 * Language model
 * 
 * @author Goran Arsenic
 *
 */

@Entity
@Table(name = "languages")
public class Language extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String languageTitle;
	
	//constructors
	public Language() {
	
	}
	
	public Language(String languageTitle) {
		this.languageTitle = languageTitle;
	}
	
	
	//getters & setters
	
	public String getLanguageTitle() {
		return languageTitle;
	}
	public void setLanguageTitle(String languageTitle) {
		this.languageTitle = languageTitle;
	}

	@Override
	public String toString() {
		return "Language [languageTitle=" + languageTitle + "\nID=" + getId() + "]";
	}
	
}

