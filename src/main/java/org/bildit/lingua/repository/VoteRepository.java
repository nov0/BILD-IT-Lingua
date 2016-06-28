package org.bildit.lingua.repository;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.model.Vote;

/**
 * @interface VoteRepository
 * @author Mladen Todorovic
 * */
public interface VoteRepository extends BaseRepository <Vote, Long> {
	
	/** @author Mladen Todorovic */
	Vote findVoteByTicket(Ticket ticket);
	
}
