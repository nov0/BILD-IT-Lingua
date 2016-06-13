package org.bildit.lingua.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.service.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class PracticeController {
	
	private static Stack<Ticket> stack = new Stack<>();
	
	@Autowired
	PracticeService practiceService;
	
	@RequestMapping("/fragments/overview-practice.html")
	public ModelAndView startOverview(
			@RequestParam(value="from", required=false) String from, 
			@RequestParam(value="category", required=false) String category, 
			@RequestParam(value="speed", required=false) String speed, 
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
	
	@RequestMapping("/fragments/flipcard-practice.html")
	public ModelAndView startFlipcard(
			@RequestParam(value="from", required=false) String from, 
			@RequestParam(value="category", required=false) String category, 
			@RequestParam(value="order", required=false) String order, 
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
	
}
