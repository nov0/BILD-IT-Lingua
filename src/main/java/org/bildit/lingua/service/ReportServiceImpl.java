package org.bildit.lingua.service;

import java.util.ArrayList;
import java.util.List;

import org.bildit.lingua.model.Language;
import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.model.User;
import org.bildit.lingua.repository.LanguageRepository;
import org.bildit.lingua.repository.TicketRepository;
import org.bildit.lingua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private UserRepository userRepository;

	/**
	 * @author Bojan Aleksic
	 * Top 20 Entries Selected by Language, based on Reputation
	 */
	@Override
	public List<Ticket> getTopEntries(String languageRequest) {
		return ticketRepository.findAllByLanguageAndTicketRating(languageRepository.getOneByLanguageTitle(languageRequest), new PageRequest(0, 20));
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
	/**
	 * @author Mladen Todorovic
	 * Method: Get banned users by all criteria
	 * */
	@Override
	public List<User> getBannedUsers(String bannedUsers) {
		
		List<User> users = new ArrayList<>();
		
		if ("all".equals(bannedUsers)) {
			users = userRepository.findAllBannedUsers(new PageRequest(0, 20));
		} else if ("voting".equals(bannedUsers)) {
			users = userRepository.findAllVotingBanUsers(new PageRequest(0, 20));
		} else if ("adding".equals(bannedUsers)) {
			users = userRepository.findAllAddingBanUsers(new PageRequest(0, 20));
		} else if ("login".equals(bannedUsers)) {
			users = userRepository.findAllLoginBanUsers(new PageRequest(0, 20));
		}
		
		return users;
	}
	
}
