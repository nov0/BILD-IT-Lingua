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
	public ModelAndView startPractice(@RequestParam() String from, @RequestParam String category, @RequestParam String speed, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		if(stack.isEmpty()) {
			for(Ticket ticket : practiceService.getTicketsForPractice(from, category, principal.getName())) {
				stack.push(ticket);
			}
		}
		modelAndView.addObject("tickets", stack.pop());
		return modelAndView;
	}
	
	
	
//	@RequestMapping("/fragments/overview-practice.html")
//	@ResponseBody
//	public String startPractice(@RequestParam String from, @RequestParam String category, @RequestParam String speed, Principal principal) {
//		java.util.Map<String, Object> map = new java.util.HashMap<>();
//		map.put("tickets", practiceService.getTicketsForPractice(from, category, principal.getName()));
//		ObjectMapper mapper = new ObjectMapper();
//		String json = "";
//		try {
//			json = mapper.writeValueAsString(map);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		return json;
//	}
	
//	@RequestMapping("/fragments/overview-practice.html")
//	@ResponseBody
//	public ModelAndView startPractice(@RequestParam String from, @RequestParam String category, @RequestParam String order, @RequestParam String speed, Principal principal) {
//		ModelAndView mav = new ModelAndView();
//		List<Ticket> ticketsForPractice = practiceService.getTicketsForPractice(from, category, principal.getName());
//		mav.addObject("tickets", ticketsForPractice);
//		return mav;
//	}
	
//	@RequestMapping("/practice")
//	@ResponseBody
//	public java.util.Map<String, Object> startPractice(@RequestParam String from, @RequestParam String category, @RequestParam String order, @RequestParam String speed, Principal principal) {
//		java.util.Map<String, Object> map = new java.util.HashMap<>();
//		List<Ticket> ticketsForPractice = practiceService.getTicketsForPractice(from, category, principal.getName());
//		map.put("tickets", ticketsForPractice);
//		return map;
//	}
	
}
