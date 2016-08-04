package org.bildit.lingua.pdf.helper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.bildit.lingua.model.Ticket;
import org.bildit.lingua.model.User;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePdf {
	
	private static Font PDF_TITLE = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
	private static Font PDF_TITLE_SMALL = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
	private static Font TABLE_HEADER = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);

	public static Document createPdf(String file, String downloadRequest, List<?> records, String pdfTitle) {
		
		Document document = null;
		
		try {
			document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			
			addMetaData(document);
			addTitlePage(document, pdfTitle);
			
			if("top-users".equals(downloadRequest)) {
				createTableForTopUsers(document, records);
			} else if("top-entries".equals(downloadRequest)) {
				createTableTopEntries(document, records);
			} else if("banned-users".equals(downloadRequest)) {
				createTableBannedUsers(document, records, pdfTitle);
			} else if("statistic".equals(downloadRequest)) {
				createPieChart(document, records, writer);
			}
			document.close();
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
		
		return document;	
	}
	
	private static void addMetaData(Document document) {
		document.addTitle("PDF Report");
		document.addAuthor("author name");
	}
	
	private static void addTitlePage(Document document, String pdfTitle) throws DocumentException {
		Paragraph preface = new Paragraph();
		createEmptyLine(preface, 1);
		preface.add(new Paragraph(pdfTitle.split("\\.")[0].replaceAll("-", " "), PDF_TITLE));
		
		createEmptyLine(preface, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		preface.add(new Paragraph("Report created on " + sdf.format(new java.util.Date()), PDF_TITLE_SMALL));
		document.add(preface);
	}
	
	private static void createEmptyLine(Paragraph paragraph, int number) {
		for(int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
	/**
	 * @author Bojan Aleksic
	 * @param document
	 * @param records
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static void createTableTopEntries(Document document, List<?> records) throws DocumentException {

		List<Ticket> tickets = (List<Ticket>) records;
		
		int numberOfRecords = 20;
		
		if(records.size() < 20) {
			numberOfRecords = records.size();
		}
		
		Paragraph paragraph = new Paragraph();
		createEmptyLine(paragraph, 2);
		document.add(paragraph);
		
		PdfPTable table = new PdfPTable(8);
		table.setWidths(new int[] {40, 150, 100, 100, 100, 50, 50, 130});
		
		PdfPCell cell = new PdfPCell(new Phrase("#", TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Author", TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Domestic Text", TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Foreign Text", TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Category", TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Likes", TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Dislikes", TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Date of created", TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		table.setHeaderRows(1);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		for(int i = 0; i < numberOfRecords; i++) {
			table.setWidthPercentage(100);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(String.valueOf(i + 1));
			table.addCell(tickets.get(i).getUser().getFirstName() + " " + tickets.get(i).getUser().getLastName());
			table.addCell(tickets.get(i).getTextDomestic());
			table.addCell(tickets.get(i).getTextForeign());
			table.addCell(tickets.get(i).getCategory());
			table.addCell(String.valueOf(tickets.get(i).getTicketVotes().getLikes()));
			table.addCell(String.valueOf(tickets.get(i).getTicketVotes().getDislikes()));
			table.addCell(tickets.get(i).getLocalDateTime().format(formatter));
		}
		
		document.add(table);
	}
	
	/**
	 * @author Novislav Sekulic
	 * @param document
	 * @param records
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static void createTableForTopUsers(Document document, List<?> records) throws DocumentException {
		
		List<String> entries = (List<String>) records;
		
		int numberOfRecords = 20;
		
		if(entries.size() < 20) {
			numberOfRecords = entries.size();
		}
		
		Paragraph paragraph = new Paragraph();
		createEmptyLine(paragraph, 2);
		document.add(paragraph);
		
		PdfPTable table = new PdfPTable(7);
		table.setWidths(new int[] {40, 150, 100, 100, 60, 60, 100});
		
		PdfPCell cell = new PdfPCell(new Phrase("#", TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("User", TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Domestic Language", TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Lerning Language", TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Likes", TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Dislikes", TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Reputation", TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		table.setHeaderRows(1);
		
		String[] entriesInfo;
		
		for(int i = 0; i < numberOfRecords; i++) {
			entriesInfo = entries.get(i).split("_");
			table.setWidthPercentage(100);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(String.valueOf(i + 1));
			table.addCell(entriesInfo[0]);
			table.addCell(entriesInfo[1]);
			table.addCell(entriesInfo[2]);
			table.addCell(entriesInfo[3]);
			table.addCell(entriesInfo[4]);
			table.addCell(entriesInfo[5]);
		}
		
		document.add(table);
	}
	
	/**
	 * @author Mladen Todorovic
	 * @param document
	 * @param records
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static void createTableBannedUsers(Document document, List<?> records, String pdfTitle) throws DocumentException {
		
		List<User> users = (List<User>) records;
		
		int numberOfRecords = 20;
		
		if(users.size() < 20) {
			numberOfRecords = users.size();
		}
		
		Paragraph paragraph = new Paragraph();
		createEmptyLine(paragraph, 2);
		document.add(paragraph);
		PdfPTable table = null;
		
		if ("First-20-Banned-Users-by-All-Ban-Criteria.pdf".matches(pdfTitle)) {
			
			table = new PdfPTable(10);
			table.setWidths(new int[] {40, 150, 90, 90, 60, 65, 60, 60, 60, 60});
			
			PdfPCell cell = new PdfPCell(new Phrase("#", TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("User", TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Domestic Language", TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Learning Language", TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Total Likes", TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Total Dislikes", TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Total Tickets", TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Voting Ban", TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Adding Ban", TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Login Ban", TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			table.setHeaderRows(1);
			
			for(int i = 0; i < numberOfRecords; i++) {
				table.setWidthPercentage(100);
				table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(String.valueOf(i + 1));
				table.addCell(users.get(i).getFirstName() + " " + users.get(i).getLastName());
				table.addCell(users.get(i).getDomesticLanguage().getLanguageTitle());
				table.addCell(users.get(i).getForeignLanguage().getLanguageTitle());
				table.addCell(String.valueOf(users.get(i).sumOfAllUserTicketsLikes()));
				table.addCell(String.valueOf(users.get(i).sumOfAllUserTicketsDislikes()));
				table.addCell(String.valueOf(users.get(i).sumOfAllUserTickets()));
				table.addCell(users.get(i).isVotingBan() == true ? "Yes" : "No");
				table.addCell(users.get(i).isAddingBan() == true ? "Yes" : "No");
				table.addCell(users.get(i).isEnabled() != true ? "Yes" : "No");
			}
			
		} else {
			
			table = new PdfPTable(8);
			table.setWidths(new int[] {40, 150, 100, 100, 60, 65, 60, 100});
			
			PdfPCell cell = new PdfPCell(new Phrase("#", TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("User", TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Domestic Language", TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Learning Language", TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Total Likes", TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Total Dislikes", TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Total Tickets", TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Date of Ban", TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			table.setHeaderRows(1);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			
			for(int i = 0; i < numberOfRecords; i++) {
				table.setWidthPercentage(100);
				table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(String.valueOf(i + 1));
				table.addCell(users.get(i).getFirstName() + " " + users.get(i).getLastName());
				table.addCell(users.get(i).getDomesticLanguage().getLanguageTitle());
				table.addCell(users.get(i).getForeignLanguage().getLanguageTitle());
				table.addCell(String.valueOf(users.get(i).sumOfAllUserTicketsLikes()));
				table.addCell(String.valueOf(users.get(i).sumOfAllUserTicketsDislikes()));
				table.addCell(String.valueOf(users.get(i).sumOfAllUserTickets()));
				if ("First-20-Banned-Users-by-Voting-Ban-Criteria.pdf".matches(pdfTitle)) {
					table.addCell(users.get(i).getDateOfVotingBan().format(formatter));
				} else if ("First-20-Banned-Users-by-Adding-Ban-Criteria.pdf".matches(pdfTitle)) {
					table.addCell(users.get(i).getDateOfAddingBan().format(formatter));
				} else if ("First-20-Banned-Users-by-Login-Ban-Criteria.pdf".matches(pdfTitle)) {
					table.addCell(users.get(i).getDateOfLoginBan().format(formatter));					
				}
			}
		}
		
		document.add(table);
	}
	
	public static ByteArrayOutputStream convertPdfToByteArrayOutputStream(String fileName) {
		
		InputStream inputStream = null;
		ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
		
		try {
			inputStream = new FileInputStream(fileName);
			byte[] buffer = new byte[1024];
			byteOutStream = new ByteArrayOutputStream();
			
			int bytesRead;
			while((bytesRead = inputStream.read(buffer)) != -1) {
				byteOutStream.write(buffer, 0, bytesRead);
			}
		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
		} catch(IOException ex) {
			ex.printStackTrace();
		} finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		return byteOutStream;
	}
	
	/**
	 * Method for adding chart image to document
	 * 
	 * @author Goran Arsenic
	 * @param document
	 * @param records
	 * @param writer
	 * @throws IOException
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	private static void createPieChart(Document document, List<?> records, PdfWriter writer) throws IOException, DocumentException {

		List<Object> data = (List<Object>) records;
		int width = 525;
        int height = 525; 
        
		JFreeChart chart = getChart(data);
        BufferedImage bufferedImage = chart.createBufferedImage(width, height);
        Image image = Image.getInstance(writer, bufferedImage, 1.0f);

        // add empty space before chart
        Paragraph paragraph = new Paragraph();
        createEmptyLine(paragraph, 2);
        document.add(paragraph);
        document.add(image);
	}
	
	/**
	 * Method for creating chart
	 * 
	 * @author Goran Arsenic
	 * @param data
	 * @return
	 */
	private static JFreeChart getChart(List<Object> data) {

		DefaultPieDataset dataset = new DefaultPieDataset();
		
		int i = 0;
		String languageTitle = "";
		int numberOfTickets = 0;
		// language title and number of tickets are in the same list, so i use this to separate them
		for(Object object : data) {
			if(i % 2 == 0) {
				languageTitle = (String) object;
			} else {
				numberOfTickets = (int) object;
				dataset.setValue(languageTitle, numberOfTickets);
			}
			i++;
		}	
		// BUG: tooltips are set to false, but they are visible on the chart
		JFreeChart chart = ChartFactory.createPieChart("Percent of entries for every language", dataset, true, false, false);
		return chart;
	}
	
}
