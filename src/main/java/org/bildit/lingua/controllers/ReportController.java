package org.bildit.lingua.controllers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bildit.lingua.model.User;
import org.bildit.lingua.pdf.helper.GeneratePdf;
import org.bildit.lingua.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	/**
	 * @author Bojan Aleksic
	 * @param request
	 * @param response
	 * @param downloadRequest
	 * @param languageRequest
	 * @throws IOException
	 */
	@RequestMapping(value="/download")
	public void downloadPdf(HttpServletRequest request, 
			HttpServletResponse response, 
			@RequestParam("request") String downloadRequest,
			@RequestParam(value="bannedUsers", required=false) String bannedUsers,
			@RequestParam(value="languageRequest", required=false) String languageRequest) throws IOException {
		
		List<?> records = null;
		
		String fileName = "";
		
		if("top-users".equals(downloadRequest)) {
			if(languageRequest.equals("Svi")) {
				languageRequest = "All";
			}
			fileName = "Top-20-Users-Ordered-by-" + (languageRequest.contains("All") ? "" : languageRequest + "-Language-And-") + "Reputation.pdf";
			records = prepareListOfTopUsers(languageRequest);
		} else if("top-entries".equals(downloadRequest)) {
			fileName = "Top-20-entries-for-" + languageRequest + "-language-based-on-reputation.pdf";
			records = reportService.getTopEntries(languageRequest);
		} else if("banned-users".equals(downloadRequest)) {
			if ("all".equals(bannedUsers)) {
				fileName = "First 20 Banned Users by all three criteries.pdf";				
			} else if ("voting".equals(bannedUsers)) {
				fileName = "First 20 Banned Users by Voting-Ban criteria.pdf";
			} else if ("adding".equals(bannedUsers)) {
				fileName = "First 20 Banned Users by Adding-Ban criteria.pdf";
			} else if ("login".equals(bannedUsers)) {
				fileName = "First 20 Banned Users by Login-Ban criteria.pdf";
			}
			records = reportService.getBannedUsers(bannedUsers);
		} else if("statistic".equals(downloadRequest)) {
			fileName = "General-statistic-of-application.pdf";
			records = reportService.getDataForPieChart();
		}
		
		final ServletContext servletContext = request.getSession().getServletContext();
		final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		final String tempFilePath = tempDirectory.getAbsolutePath();
		
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);
		
		try {
			GeneratePdf.createPdf(tempFilePath + "\\" + fileName, downloadRequest, records, fileName);
			ByteArrayOutputStream byteOutStream = GeneratePdf.convertPdfToByteArrayOutputStream(tempFilePath + "\\" + fileName);
			OutputStream outputStream = response.getOutputStream();
			byteOutStream.writeTo(outputStream);
			outputStream.flush();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * @author Novislav Sekulic
	 * @param language
	 * @return
	 */
	private List<String> prepareListOfTopUsers(String language){
		List<String> report = new ArrayList<>();
		List<User> users = new ArrayList<>();
		if(language.contains("All")) {
			users =  reportService.getTopUsersByReputation();
		} else {
			users = reportService.getTopUsersByReputationAndLanguage(language);
		}
		String reportStatus = "";
		
		int likes = 0;
		int dislikes = 0;
		
		for(User u : users) {
			reportStatus += u.getFirstName() + " " + u.getLastName() + "_";
			reportStatus += u.getDomesticLanguage().getLanguageTitle() + "_" + u.getForeignLanguage().getLanguageTitle() + "_";
			if(language.contains("All")) {
				likes = u.sumOfAllUserTicketsLikes();
				dislikes = u.sumOfAllUserTicketsDislikes();
			} else {
				likes = reportService.getUsersLikesByLanguages(u, language);
				dislikes = reportService.getUsersDislikesByLanguage(u, language);
			}
			reportStatus += likes + "_" + dislikes + "_" + (likes - dislikes);
			report.add(reportStatus);
			reportStatus = "";
		}
		
		return report;
	}
	
}
