package org.bildit.lingua.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
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
	@Lob
	private byte[] languageIcon;
	
	@OneToOne(mappedBy = "defaultLanguage")
	private User user;
	
	//constructors
	public Language() {
	
	}	
	public Language(String languageTitle, byte[] languageIcon) {
		this.languageTitle = languageTitle;
		this.languageIcon = languageIcon;
	}
	
	
	//getters & setters
	
	public String getLanguageTitle() {
		return languageTitle;
	}
	public void setLanguageTitle(String languageTitle) {
		this.languageTitle = languageTitle;
	}
	public byte[] getLanguageIcon() {
		return languageIcon;
	}
	public void setLanguageIcon(byte[] languageIcon) {
		this.languageIcon = languageIcon;
	}
	
}

