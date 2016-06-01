package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.Language;

public interface LanguageService extends BaseService<Language, Long> {
	
	public Language getOneByLanguageTitle(String languageTitle);
	
	List<String> getAllLanguages();

}
