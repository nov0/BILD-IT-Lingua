package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.repository.LanguageRepository;
import org.bildit.lingua.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private LanguageRepository languageRepository;

	/**
	 * @author Bojan Aleksic
	 * Top 20 Entries Selected by Language, baseded on Reputation
	 */
	@Override
	public List<Ticket> getTopEntries(String languageRequest) {
		return ticketRepository.findAllByLanguageAndTicketRating(languageRepository.getOneByLanguageTitle(languageRequest), new PageRequest(0, 20));
	}
	
}
