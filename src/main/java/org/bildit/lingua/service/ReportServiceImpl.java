package org.bildit.lingua.service;

import java.util.List;

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
	
}
