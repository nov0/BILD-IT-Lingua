package org.bildit.lingua.service;

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
	
}
