package org.bildit.lingua.pdf.helper;

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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePdf {
	
	private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	public static Document createPdf(String file, String downloadRequest, List<Ticket> records) {
		
		Document document = null;
		
		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			
			addMetaData(document);
			addTitlePage(document);
			
			if("top-users".equals(downloadRequest)) {
				// invoke method for top users here
			} else if("top-entries".equals(downloadRequest)) {
				createTableTopEntries(document, records);
			} else if("banned-users".equals(downloadRequest)) {
				// invoked method for banned users here
			} else if("statistic".equals(downloadRequest)) {
				// invoked statistic here...
			}
			document.close();
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
		
		return document;	
	}
	
	private static void addMetaData(Document document) {
		document.addTitle("PDF Report");
		document.addAuthor("author name");
	}
	
	private static void addTitlePage(Document document) throws DocumentException {
		Paragraph preface = new Paragraph();
		createEmptyLine(preface, 1);
		preface.add(new Paragraph("PDF Report", TIME_ROMAN));
		
		createEmptyLine(preface, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		preface.add(new Paragraph("Report created on " + sdf.format(new java.util.Date()), TIME_ROMAN_SMALL));
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
	 * @param topEntries
	 * @throws DocumentException
	 */
	public static void createTableTopEntries(Document document, List<Ticket> topEntries) throws DocumentException {
		
		int numberOfRecords = 20;
		
		if(topEntries.size() < 20) {
			numberOfRecords = topEntries.size();
		}
		
		Paragraph paragraph = new Paragraph();
		createEmptyLine(paragraph, 2);
		document.add(paragraph);
		
		PdfPTable table = new PdfPTable(8);
		
		PdfPCell cell = new PdfPCell(new Phrase("#"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Author"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Domestic Text"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Foreign Text"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Category"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Likes"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Dislikes"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Date of created"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		table.setHeaderRows(1);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		for(int i = 0; i < numberOfRecords; i++) {
			table.setWidthPercentage(100);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(String.valueOf(i + 1));
			table.addCell(topEntries.get(i).getUser().getFirstName() + " " + topEntries.get(i).getUser().getLastName());
			table.addCell(topEntries.get(i).getTextDomestic());
			table.addCell(topEntries.get(i).getTextForeign());
			table.addCell(topEntries.get(i).getCategory());
			table.addCell(String.valueOf(topEntries.get(i).getTicketVotes().getLikes()));
			table.addCell(String.valueOf(topEntries.get(i).getTicketVotes().getDislikes()));
			table.addCell(topEntries.get(i).getLocalDateTime().format(formatter));
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
	
}
