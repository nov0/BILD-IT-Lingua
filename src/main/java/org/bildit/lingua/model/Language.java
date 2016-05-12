package org.bildit.lingua.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Language model
 * 
 * @author Goran Arsenic
 *
 */

@Entity
@Table(name = "languages")
public class Language {
	
	@Id
	private long languageId;
	private String languageTitle;
	@Lob
	private byte[] languageIcon;
		
	//constructors
	public Language() {
	
	}	
	public Language(String languageTitle, byte[] languageIcon) {
		this.languageTitle = languageTitle;
		this.languageIcon = languageIcon;
	}
	
	
	//getters & setters
	public long getLanguageId() {
		return languageId;
	}
	public void setLanguageId(long languageId) {
		this.languageId = languageId;
	}
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

