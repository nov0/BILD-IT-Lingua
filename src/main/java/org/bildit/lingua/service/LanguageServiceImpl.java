package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.Language;
import org.bildit.lingua.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of LanguageService interface
 * 
 * @author Goran Arsenic
 *
 */
@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	LanguageRepository languageRepository;
	
	@Override
	public List<Language> findAllLanguages() {
		return languageRepository.findAll();
	}

	@Override
	public Language findLanguage(Long id) {
		return languageRepository.findOne(id);
	}

	@Override
	public void saveLanguage(Language newLanguage) {
		languageRepository.save(newLanguage);
		
	}
	

}
