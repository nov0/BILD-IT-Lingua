package org.bildit.lingua.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PracticeController {

	@RequestMapping(value="/practice", method=RequestMethod.POST)
	public String startPractice(
			@RequestParam("from") String from,
			@RequestParam("category") String category,
			@RequestParam("speed") String speed,
			@RequestParam("order") String order) {
		
		System.out.println(from + " " + category + " " + speed + " " + order);
		return "home";
	}
}
