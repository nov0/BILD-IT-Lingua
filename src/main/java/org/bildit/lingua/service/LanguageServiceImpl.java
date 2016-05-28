package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.Language;
import org.bildit.lingua.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageRepository languageRepository;
	
	@Override
	public List<Language> getAll() {
		return languageRepository.findAll();
	}

	@Override
	public Language getOne(Long id) {
		return languageRepository.getOne(id);
	}

	@Override
	public void delete(Long id) {
		languageRepository.delete(id);
	}
	
	@Override
	public Language getOneByLanguageTitle(String languageTitle) {
		return languageRepository.getOneByLanguageTitle(languageTitle);
	}
	
	
	
}
