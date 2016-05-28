package org.bildit.lingua.repository;

import org.bildit.lingua.model.Language;
import org.springframework.data.jpa.repository.Query;

/**
 * 
 * @interface LanguageRepository
 * 
 * @author Mladen Todorovic
 * 
 * */
public interface LanguageRepository extends BaseRepository<Language, Long>, LanguageRepositoryCustom {

	@Query("SELECT lang FROM Language lang WHERE lang.languageTitle = ?1")
	Language getOneByLanguageTitle(String languageTitle);
	
}
