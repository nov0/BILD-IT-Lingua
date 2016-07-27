package org.bildit.lingua.service;

import java.util.ArrayList;
import java.util.List;

import org.bildit.lingua.model.Language;
import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.repository.LanguageRepository;
import org.bildit.lingua.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private LanguageRepository languageRepository;

	@Override
	public List<Ticket> getTopEntries(String languageRequest) {
		return ticketRepository.findAllByLearningLanguage(languageRepository.getOneByLanguageTitle(languageRequest));
	}
	
	/**
	 * Method for getting data for general statistic report
	 * 
	 * @author Goran Arsenic
	 */
	@Override
	public List<Object> getDataForPieChart() {

		List<Object> data = new ArrayList<>();
		
		List<Language> listOfAllLanguages = languageRepository.findAll();
		
		for(Language language : listOfAllLanguages) {
			data.add(language.getLanguageTitle());
			data.add(ticketRepository.getNumberOfTicketsForLanguage(language));
		}
		
		return data;
	}
	
}
