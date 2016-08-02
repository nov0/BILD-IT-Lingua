package org.bildit.lingua.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.repository.UserRepository;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TicketController {
	
	private static final Logger logger = Logger.getLogger(TicketController.class.getName());
	private static final String TICKETS = "tickets";
	private static final String REDIRECT = "redirect:/";
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * @author Bojan Aleksic
	 * @edit Mladen Todorovic
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
			@RequestParam(value="learningLanguage", required=false) String learningLanguage,
			@PageableDefault(value=4) Pageable pageable) {

		Page<Ticket> tickets = null;
		String language;
		
		if (learningLanguage == null || learningLanguage.isEmpty()) {
			language = userRepository.findUserByUsername(principal.getName()).getForeignLanguage().getLanguageTitle();
		} else {
			userService.setForeignLanguageForUser(principal.getName(), learningLanguage);
			language = learningLanguage;
		}
		
		if("ticket-all".equals(urlRequest)) {
			tickets = ticketService.getAllTicketsByUsername(principal.getName(), language, pageable);
		} else if("ticket-active".equals(urlRequest)) {
			tickets = ticketService.getAllActiveTicketsByUsername(principal.getName(), language, pageable);
		} else if("ticket-deleted".equals(urlRequest)) {
			tickets = ticketService.getAllDeactivatedTicketsByUsername(principal.getName(), language, pageable);
		} else if("ticket-moderated".equals(urlRequest)) {
			tickets = ticketService.getAllModeratedTicketsByUsername(principal.getName(), language, pageable);
		}
		
		if(tickets != null ? tickets.getContent().size() > 0 : false) {
			model.addObject(TICKETS, tickets);
			model.addObject("totalPages", tickets.getTotalPages());
			model.addObject("foreignLanguage", language);
		}
		
		return model;
	}
	
	/**
	 * @author Bojan Aleksic
	 * @param ticket
	 * @param principal
	 * @return
	 */
	@RequestMapping("/create-ticket")
	public String createNewTicket(@ModelAttribute("ticket") Ticket ticket, Principal principal, HttpSession session) {
		ticketService.saveTicket(ticket, principal.getName());
		return REDIRECT + "?ticket-added";
	}
	
	/**
	 * @author Bojan Aleksic
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/edit-ticket")
	public String editTicket(Model model, @RequestParam("id") Long id, Authentication auth) {
		model.addAttribute("ticket", ticketService.getOne(id));
		model.addAttribute("languages", languageService.getAllLanguages());
		model.addAttribute("authority", auth.getAuthorities().toString());
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
				logger.error(error);
			}
		}
		ticketService.updateTicket(ticket.getTextDomestic(), ticket.getTextForeign(), ticket.getCategory(), ticket.getLocalDateTime(), id);
		return REDIRECT + "?ticket-edited";
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
		return REDIRECT;
	}
	
	/**
	 * @author Novislav Sekulic
	 * @param ticket
	 * @param id
	 * @return
	 */
	@RequestMapping("/disable-ticket")
	public String disableTicket(@ModelAttribute Ticket ticket, @RequestParam("id") Long id) {
		ticketService.disableTicketByAdmin(id);
		return REDIRECT;
	}
	
	@RequestMapping("/enable-ticket")
	public String enableTicket(@RequestParam("id") Long id) {
		ticketService.enableTicket(id);
		return REDIRECT;
	}
	
	/**
	 * @author Mladen Todorovic
	 * Method: add like to ticket by ticket-id and user's username
	 */
	@RequestMapping("/add-like")
	@ResponseBody
	public String addLike(@RequestParam("id") String ticketId, Principal principal) {
		Long id = Long.parseLong(ticketId);
		return ticketService.addLikeToTicket(id, principal.getName());
	}
	
	/**
	 * @author Mladen Todorovic
	 * Method: add dislike to ticket by ticket-id and user's username
	 */
	@RequestMapping("/add-dislike")
	@ResponseBody
	public String addDislike(@RequestParam("id") String ticketId, Principal principal) {
		Long id = Long.parseLong(ticketId);
		return ticketService.addDislikeToTicket(id, principal.getName());
	}
	
	/**
	 * @author Novislav Sekulic
	 * Method for showing ticket for admin.
	 */
	@RequestMapping("/fragments/get-tickets-admin")
	@ResponseBody
	public ModelAndView getTicketsForAdmin(
			ModelAndView model,
			@RequestParam("urlData") String urlRequest,
			@RequestParam(value="page", required=false) Integer page,
			@PageableDefault(value=4) Pageable pageable
			) {
		
		return ticketService.getTicketsForAdmin(model, urlRequest, page, pageable);
	}
}