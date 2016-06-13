package org.bildit.lingua.service;

import org.bildit.lingua.model.Vote;

/**
 * @interface VoteService
 * @author Mladen Todorovic
 * */

public interface VoteService extends BaseService <Vote, Long> {
	
	Vote findVoteByTicketId(Long id);
	
}
