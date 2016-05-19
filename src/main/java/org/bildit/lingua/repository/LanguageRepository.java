package org.bildit.lingua.repository;

import org.bildit.lingua.model.Language;

/**
 * 
 * @interface  LanguageRepository
 * 
 * @author Goran Arsenic
 * 
 * */

public interface LanguageRepository extends BaseRepository <Language, Long>, CustomLanguageRepository {
	
}
