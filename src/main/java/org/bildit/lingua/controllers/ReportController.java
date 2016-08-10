package org.bildit.lingua.controllers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			records = reportService.prepareListOfTopUsers(languageRequest);
		} else if("top-entries".equals(downloadRequest)) {
			fileName = "Top-20-entries-for-" + languageRequest + "-language-based-on-reputation.pdf";
			records = reportService.getTopEntries(languageRequest);
		} else if("banned-users".equals(downloadRequest)) {
			if (!bannedUsers.isEmpty()) {
				fileName = "First-20-Banned-Users-by-" + bannedUsers + "-Ban-Criteria.pdf";			
			} else {
				fileName = "First-20-Banned-Users-by-All-Ban-Criteria.pdf";	
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
	
}
