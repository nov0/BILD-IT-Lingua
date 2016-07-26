package org.bildit.lingua.service;

import java.util.List;

import org.bildit.lingua.model.Ticket;

public interface ReportService {

	List<Ticket> getTopEntries(String languageRequest);
	
}
