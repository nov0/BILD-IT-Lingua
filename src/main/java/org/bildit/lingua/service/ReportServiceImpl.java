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
	 * Top 20 Entries Selected by Language, baseded on Reputation
	 */
	@Override
	public List<Ticket> getTopEntries(String languageRequest) {
		return ticketRepository.findAllByLanguageAndTicketRating(languageRepository.getOneByLanguageTitle(languageRequest), new PageRequest(0, 20));
	}
	
	/**
	 * @author Novislav Sekulic
	 * Get top 20 users by reputation and specified language.
	 */
	@Override
	public List<User> getTopUsersByReputationAndLanguage(String languageRequest) {
		return userRepository.findAllByTotalVotesEntriesByLanguage(languageRepository.getOneByLanguageTitle(languageRequest), new PageRequest(0, 20));
	}
	
	/**
	 * @author Novislav Sekulic
	 * Get top 20 users by reputation.
	 */
	@Override
	public List<User> getTopUsersByReputation() {
		return userRepository.findAllByTotalVotesEntries(new PageRequest(0, 20));
	}
	/**
	 * @author Novislav Sekulic
	 * Get sum of all dislikes from user in specified language.
	 */
	@Override
	public Integer getUsersDislikesByLanguage(User user, String language) {
		Integer sumOfDislikes = ticketRepository.getSumOfTicketDislikesFromUserAndLanguage(user, languageRepository.getOneByLanguageTitle(language));
		return sumOfDislikes != null ? sumOfDislikes : 0;
	}
	
	/**
	 * @author Novislav Sekulic
	 * Get sum of all likes from user in specified language.
	 */
	@Override
	public Integer getUsersLikesByLanguages(User user, String language) {
		Integer sumOfLikes = ticketRepository.getSumOfTicketLikesFromUserAndLanguage(user, languageRepository.getOneByLanguageTitle(language));
		return sumOfLikes != null ? sumOfLikes : 0;
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