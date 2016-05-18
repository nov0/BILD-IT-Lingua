package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.Language;
import org.springframework.stereotype.Service;

/**
 * Implemented by LanguageServiceImpl
 * 
 * @author Goran Arsenic
 *
 */
@Service
public interface LanguageService {

	List<Language> findAllLanguages();
	Language findLanguage(Long id);
	void saveLanguage(Language newLanguage);
	
}
