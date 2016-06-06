package org.bildit.lingua.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PracticeController {

	
	//TODO: Now is just for test form submit, must be changed
	@RequestMapping(value="/practice", method=RequestMethod.POST)
	public String startPractice(
			@RequestParam("from") String from,
			@RequestParam("category") String category,
			@RequestParam("speed") String speed,
			@RequestParam("order") String order) {
		
		System.out.println(from + " " + category + " " + speed + " " + order);
		return "error";
	}
}
