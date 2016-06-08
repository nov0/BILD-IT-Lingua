package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.Language;
import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.model.User;
import org.bildit.lingua.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PracticeServiceImpl implements PracticeService {

	@Autowired
	UserService userService;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Override
	public List<Ticket> getTicketsForPractice(String from, String category, String username) {
		
		User user = userService.findUserByUsername(username);
		
		Language foreignLanguage = user.getForeignLanguage();
		
		// set how many tickets read from database
		Pageable pageable = new PageRequest(0, 10);
		
		if(from.equals("me") && !category.equals("all")) {
			return ticketRepository.getMyTicketsForPractice(user, category, foreignLanguage, pageable);
		} else if(from.equals("me") && category.equals("all")) {
			return ticketRepository.getMyTicketsForPractice(user, foreignLanguage, pageable);
		} else if(!from.equals("me") && !category.equals("all")) {
			return ticketRepository.getTicketsForPractice(category, foreignLanguage, pageable);
		} else {
			return ticketRepository.getTicketsForPractice(foreignLanguage, pageable);
		}
	}

}
