package org.bildit.lingua.controllers;

import java.security.Principal;
import java.util.Stack;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.service.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PracticeController {
	
	private static Stack<Ticket> stack = new Stack<>();
	private static String sideOrder = "";
	
	@Autowired
	PracticeService practiceService;
	
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
			@RequestParam(value="speedOrOrder", required=false) String speed, 
			Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		if(stack.isEmpty()) {
			for(Ticket ticket : practiceService.getTicketsForPractice(from, category, principal.getName())) {
				stack.push(ticket);
			}
		}
		modelAndView.addObject("tickets", stack.pop());
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
			@RequestParam(value="speedOrOrder", required=false) String order, 
			Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		if(stack.isEmpty()) {
			for(Ticket ticket : practiceService.getTicketsForPractice(from, category, principal.getName())) {
				stack.push(ticket);
			}
		}
		if(order != null){
			sideOrder = order;
		}
		modelAndView.addObject("tickets", stack.pop());
		modelAndView.addObject("stackSize", stack.size());
		modelAndView.addObject("order", sideOrder);
		return modelAndView;
	}
	
}
