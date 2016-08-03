package org.bildit.lingua.scheduledTasks;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.repository.TicketRepository;
import org.bildit.lingua.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Class for scheduled ticket delete
 *
 * @author Goran Arsenic
 * 
 * This class delete ticket from data base that administrator is mark as deactivated
 * 
 */
@Component
public class ScheduledTasks {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private TicketService ticketService;
	
	// this method is scheduled for every 60 seconds (set to 24h when project is finished)
	@Scheduled(fixedRate=1000*60)
	public void deleteTicketsFromDataBase() {
		// here you set how long it takes (in days) for system to delete ticket from data base
		int numberOfDays = 1;
		
		Date date = createDateForComparison(numberOfDays);
		List<Ticket> listOfTicketForDelete = ticketRepository.getTicketsForScheduledDelete(date);
		
		if(!listOfTicketForDelete.isEmpty()) {
			for(Ticket ticket : listOfTicketForDelete) {
				ticketService.deleteTicket(ticket.getId(), ticket.getUser().getUsername());
			}
		}
		
	}
	
	// calculate the starting date for scheduled ticket delete
	private Date createDateForComparison(int numberOfDays) {
		Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, - numberOfDays);
	    Date date = cal.getTime();
	    return date;
	}
	
}
