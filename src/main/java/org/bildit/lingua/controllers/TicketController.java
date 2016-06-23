package org.bildit.lingua.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.service.LanguageService;
import org.bildit.lingua.service.TicketService;
import org.bildit.lingua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TicketController {
	
	private static final Logger logger = Logger.getLogger(TicketController.class.getName());
	private static final String HOME = "home";
	private static final String TICKETS = "tickets";
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LanguageService languageService;
	
	/**
	 * @author Bojan Aleksic
	 * @param principal
	 * @param model
	 * @param urlRequest
	 * @param page
	 * @param pageable
	 * @return
	 * Method receives all tickets by current user, determines which category by URL request,
	 * and sends back data within the model
	 */
	@RequestMapping("/fragments/get-tickets.html")
	@ResponseBody
	public ModelAndView getAllTickets(
			Principal principal, 
			ModelAndView model, 
			@RequestParam("urlData") String urlRequest, 
			@RequestParam(value="page", required=false) Integer page, 
			@PageableDefault(value=2) Pageable pageable) {
		
		Page<Ticket> tickets = null;
		
		if("ticket-all".equals(urlRequest)) {
			tickets = ticketService.getAllTicketsByUsername(principal.getName(), pageable);
			model.addObject(TICKETS, tickets);
		} 
		if("ticket-active".equals(urlRequest)) {
			tickets = ticketService.getAllActiveTicketsByUsername(principal.getName(), pageable);
			model.addObject(TICKETS, tickets);
		} 
		if("ticket-deleted".equals(urlRequest)) {
			tickets = ticketService.getAllDeactivatedTicketsByUsername(principal.getName(), pageable);
			model.addObject(TICKETS, tickets);
		} 
		if("ticket-moderated".equals(urlRequest)) {
			tickets = ticketService.getAllModeratedTicketsByUsername(principal.getName(), pageable);
			model.addObject(TICKETS, tickets);
		}
//		model.addObject(TICKETS, tickets);
		model.addObject("totalPages", tickets.getTotalPages());
		return model;
	}
	
	/**
	 * @author Bojan Aleksic
	 * @param languageTitle
	 * @param principal
	 * @return
	 * Updating user's foreign (learning) language
	 */
	@RequestMapping(value="/set-foreign-language", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, String> setForeignLanguage(@RequestBody String languageTitle, Principal principal) {
		Map<String, String> map = new HashMap<>();
		userService.setForeignLanguageForUser(principal.getName(), languageTitle);
		map.put("languageTitle", languageTitle);
		return map;
	}
	
	/**
	 * @author Bojan Aleksic
	 * @param ticket
	 * @param principal
	 * @return
	 */
	@RequestMapping("/create-ticket")
	public String createNewTicket(@ModelAttribute("ticket") Ticket ticket, Principal principal) {
		ticketService.saveTicket(ticket, principal.getName());
		return HOME;
	}
	
	/**
	 * @author Bojan Aleksic
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/edit-ticket")
	public String editTicket(Model model, @RequestParam("id") Long id) {
		model.addAttribute("ticket", ticketService.getOne(id));
		model.addAttribute("languages", languageService.getAllLanguages());
		return "fragments/edit-ticket-modal-content";
	}
	
	/**
	 * @author Bojan Aleksic
	 * @param ticket
	 * @param result
	 * @param id
	 * @return
	 */
	@RequestMapping("/edit")
	public String editTicketSubmit(@ModelAttribute("ticket") Ticket ticket, BindingResult result, @RequestParam("id") Long id) {
		if(result.hasErrors()) {
			for(Object error : result.getAllErrors()) {
				logger.info(error);
			}
		}
		ticketService.updateTicket(ticket.getTextDomestic(), ticket.getTextForeign(), ticket.getCategory(), id);
		return HOME;
	}
	
	/**
	 * @author Bojan Aleksic
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/delete-confirmation")
	public String deleteConfirmation(@RequestParam("id") Long id, Model model) {
		model.addAttribute("ticket", ticketService.getOne(id));
		return "fragments/delete-ticket-modal-content";
	}
	
	/**
	 * @author Mladen Todorovic
	 * Method: delete ticket by ticket-id and user's username
	 */
	@RequestMapping("/delete-ticket")
	public String deleteTicket(@ModelAttribute Ticket ticket, @RequestParam("id") Long id, Principal principal) {
		ticketService.deleteTicket(id, principal.getName());
		return HOME;
	}
	
	/**
	 * @author Mladen Todorovic
	 * Method: add like to ticket by ticket-id and user's username
	 */
	@RequestMapping("/add-like")
	@ResponseBody
	public String addLike(@RequestParam("id") String ticketId, Principal principal) {
		Long id = Long.parseLong(ticketId);
		System.out.println("ticket ID: " + id);
		return ticketService.addLikeToTicket(id, principal.getName());
	}
	
	/**
	 * @author Mladen Todorovic
	 * Method: add like to ticket by ticket-id and user's username
	 */
	@RequestMapping("/add-dislike")
	@ResponseBody
	public String addDislike(@RequestParam("id") String ticketId, Principal principal) {
		Long id = Long.parseLong(ticketId);
		return ticketService.addDislikeToTicket(id, principal.getName());
	}
	
}