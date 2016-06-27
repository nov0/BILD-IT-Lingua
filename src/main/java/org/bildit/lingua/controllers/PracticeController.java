package org.bildit.lingua.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Stack;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.service.PracticeService;
import org.bildit.lingua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PracticeController {
	
	private static Stack<Ticket> stack = new Stack<>();
	private static String sideOrder = "";
	
	@Autowired
	PracticeService practiceService;
	
	@Autowired
	UserService userService;
	
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
	 * @param principal
	 * @return
	 * Method checks whether user has tickets created and if so returns true,
	 * otherwise returns false
	 */
	@RequestMapping("/user-has-tickets")
	@ResponseBody
	public boolean userHasTickets(Principal principal) {
		if(userService.findUserByUsername(principal.getName()).getTickets().isEmpty()) {
			return false;
		}
		return true;
	}
	
}
