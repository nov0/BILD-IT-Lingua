package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.model.User;

public interface ReportService {

	List<Ticket> getTopEntries(String languageRequest);
	List<Object> getDataForPieChart();
	List<User> getBannedUsers(String bannedUsers);
	
	List<User> getTopUsersByReputationAndLanguage(String languageRequest);
	List<User> getTopUsersByReputation();
	
	Integer getUsersDislikesByLanguage(User user, String language);
	Integer getUsersLikesByLanguages(User user, String language);
	
	List<String> prepareListOfTopUsers(String language);
	
}
