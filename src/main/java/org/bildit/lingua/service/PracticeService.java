package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.Ticket;

public interface PracticeService {

	List<Ticket> getTicketsForPractice(String from, String category, String username);
	
}
