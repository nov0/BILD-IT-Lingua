package org.bildit.lingua.controllers;

import org.bildit.lingua.model.Language;
import org.bildit.lingua.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	
	@Autowired
	LanguageRepository languageRepository;

	@RequestMapping(value="/add-language", method=RequestMethod.GET)
	public String showAddLanguage(Model model) {
		model.addAttribute("language", new Language());
		return "add-language";
	}
	
	@RequestMapping(value="/add-newlanguage", method=RequestMethod.POST)
	public String adddNewLanguage(@ModelAttribute Language newLanguage ) {
		
		languageRepository.save(newLanguage);
		
		return "add-language";
	}
}
