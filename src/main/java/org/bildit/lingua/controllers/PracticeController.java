package org.bildit.lingua.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Stack;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.model.User;
import org.bildit.lingua.service.PracticeService;
import org.bildit.lingua.service.TicketService;
import org.bildit.lingua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PracticeController {
	
	private static Stack<Ticket> stack = new Stack<>();
	private static String sideOrder = "";
	
	@Autowired
	PracticeService practiceService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TicketService ticketService;
	
	/**
	 * @param from
	 * @param category
	 * @param speed
	 * @param principal
	 * @return
	 * @author Bojan Aleksic
	 */
	@RequestMapping("/fragments/overview-practice.html")
	public ModelAndView startOverview(
			@RequestParam(value="from", required=false) String from, 
			@RequestParam(value="category", required=false) String category, 
			ModelAndView modelAndView,
			Principal principal) {
		
		List<Ticket> tickets = practiceService.getTicketsForPractice(from, category, principal.getName());
		
		if(stack.isEmpty() && !tickets.isEmpty()) {
			for(Ticket ticket : tickets) {
				stack.push(ticket);
			}
		}
		if(!stack.isEmpty()) {
			modelAndView.addObject("tickets", stack.pop());
		}
		modelAndView.addObject("stackSize", stack.size());
		return modelAndView;
	}
	
	/**
	 * @param from
	 * @param category
	 * @param order
	 * @param principal
	 * @return
	 * @author Bojan Aleksic
	 */
	@RequestMapping("/fragments/flipcard-practice.html")
	public ModelAndView startFlipcard(
			@RequestParam(value="from", required=false) String from, 
			@RequestParam(value="category", required=false) String category, 
			@RequestParam(value="order", required=false) String order, 
			ModelAndView modelAndView,
			Principal principal) {
		if(order != null){
			sideOrder = order;
			stack.clear();
		}
		if(stack.isEmpty()) {
			for(Ticket ticket : practiceService.getTicketsForPractice(from, category, principal.getName())) {
				stack.push(ticket);
			}
		}
		
		if(!stack.isEmpty()){
			modelAndView.addObject("tickets", stack.pop());
		}
		modelAndView.addObject("stackSize", stack.size());
		modelAndView.addObject("order", sideOrder);
		return modelAndView;
	}
	
	/**
	 * @author Bojan Aleksic
	 * @param from
	 * @param category
	 * @param principal
	 * @return
	 * Method checks whether user has tickets created and if so returns true,
	 * otherwise returns false. It also checks if user has ticket in specific
	 * category.
	 */
	@RequestMapping("/user-has-tickets")
	@ResponseBody
	public String userHasTickets(@RequestParam String from, @RequestParam String category, Principal principal) {
		User user = userService.findUserByUsername(principal.getName());
		if("me".equals(from)) {
			if("all".equals(category)) {
				return "me-category-all-language=" + !ticketService.getTicketsByUserAndLanguage(user, user.getForeignLanguage()).isEmpty();
			} else {
				return "me-specified-category-language=" + !ticketService.getTicketsByUserCategoryAndLanguage(user, category, user.getForeignLanguage()).isEmpty();
			}
		} else if("everyone".equals(from)) {
			if("all".equals(category)) {
				return "everyone-category-all-language=" + !ticketService.getEveryonesTicketsByLanguage(user.getForeignLanguage()).isEmpty();
			} else {
				return "everyone-specified-category-language=" + !ticketService.getTicketsByCategoryAndLanguage(category, user.getForeignLanguage()).isEmpty();
			}
		}
		return Boolean.toString(true);
	}
	
	/**
	 * @author Bojan Aleksic
	 * Method clears out the Stack, if user leave practice 
	 * before it ends
	 */
	@RequestMapping("/reset-practice")
	@ResponseStatus(value = HttpStatus.OK)
	public void clearStackOnPracticeAbort() {
		if(!stack.isEmpty()) {
			stack.clear();
		}
	}
	
}
