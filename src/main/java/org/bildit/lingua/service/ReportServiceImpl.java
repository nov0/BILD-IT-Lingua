package org.bildit.lingua.service;

import java.io.IOException;
import java.util.List;

import org.bildit.lingua.model.User;
import org.springframework.stereotype.Service;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

@Service
public class ReportServiceImpl implements ReportService {

	public void downloadPdf(List<User> list, String title) throws IOException {

		PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);

		PdfWriter writer = new PdfWriter("destination");
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf, PageSize.A4);
		Table table = new Table(new float[] { 4, 4, 4, 1, 1, 4, 2, 2, 2 });
		table.setWidthPercent(100);
		document.add(new Paragraph(title).setFont(font));
		for (User user : list) {
			proces(table, user);
		}
		document.add(table);
		document.close();
	}
	
	private void proces(Table table, User user) {
		table.addCell(new Cell().add(new Paragraph(user.getFirstName())));
		table.addCell(new Cell().add(new Paragraph(user.getLastName())));
		table.addCell(new Cell().add(new Paragraph(user.getUsername())));
		table.addCell(new Cell().add(new Paragraph("like")));
		table.addCell(new Cell().add(new Paragraph("dislike")));
		table.addCell(new Cell().add(new Paragraph("total number of tickets")));
		table.addCell(new Cell().add(new Paragraph(Boolean.toString(user.isAddingBan()))));
		table.addCell(new Cell().add(new Paragraph(Boolean.toString(user.isEnabled()))));
		table.addCell(new Cell().add(new Paragraph(Boolean.toString(user.isVotingBan()))));
	}

}
