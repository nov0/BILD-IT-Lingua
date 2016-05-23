package org.bildit.lingua.controllers;

import org.springframework.stereotype.Controller;

/**
 * Administrator controllers
 * 
 * @author Goran Arsenic
 *
 */

@Controller
public class AdminController {
<<<<<<< HEAD

	@Autowired
	private LanguageService languageService;
	
	@RequestMapping("/admin-page")
	public String showAdminPage() {
		return "admin-page";
	}
	

	@RequestMapping(value = "/add-language", method = RequestMethod.GET)
	public String showAddLanguage() {
		return "add-language";
	}
	
	/**
	 * Controller for adding new language to database
	 * 
	 * @param languageTitle
	 * @param files
	 * @return view "add-language"
	 */
	
	@RequestMapping(value = "/add-newlanguage", method = RequestMethod.POST)
	public String adddNewLanguage(
			@RequestParam("languageTitle") String languageTitle, 
			@RequestParam("languageIcon") MultipartFile files[]) {

		Language newLanguage = new Language();
		try {
			for (MultipartFile file : files) {
				if (!file.isEmpty()) {
					newLanguage.setLanguageTitle(languageTitle);

					newLanguage.setLanguageIcon(file.getBytes());

				}
			}
		} catch (IOException e) {
			System.err.println("Image can not be uploaded");
		}

		languageService.saveLanguage(newLanguage);

		return "redirect:/add-language";
	}
	@RequestMapping("/list-languages")
	public String showLanguages(Model model) {
		
		model.addAttribute("languages", languageService.findAllLanguages());
		
		return "show-languages";
	}
	
	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public void getImage(@PathVariable long id,
                         HttpServletResponse resp)
    {
        
        Language language = languageService.findLanguage(id);

       
        try {
        	resp.getOutputStream().write(language.getLanguageIcon());
		} catch (IOException e) {
			System.err.println("Ne valja");
		}
      
    }
=======
>>>>>>> 0da6da354c5a65f342fa12a68ac9f858b39dcaeb
	
}
