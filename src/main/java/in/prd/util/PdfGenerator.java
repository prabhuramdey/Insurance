package in.prd.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.prd.entity.CitezenPlan;
import in.prd.repository.CitezenPlanRepository;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {
	
	@Autowired
	private CitezenPlanRepository planRepo;

	public void generate(HttpServletResponse response, List<CitezenPlan> plans, File f ) throws Exception{

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream()); // response is used to send the file to the browser
		PdfWriter.getInstance(document, new FileOutputStream(f));	// used to download the file in my system
		document.open();

		// Creating font
		// Setting font style and size
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);
		// Creating paragraph
		Paragraph paragraph = new Paragraph("Citizens Plans", fontTiltle);
		// Aligning the paragraph in the document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		// Adding the created paragraph in the document
		document.add(paragraph);

		Paragraph p = new Paragraph("Citizens plan info");
		document.add(p);

		PdfPTable table = new PdfPTable(7);

		// Setting width of the table, its columns and spacing
		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 3, 3, 3, 3 });
		table.setSpacingBefore(5);
		// Create Table Cells for the table header
		PdfPCell cell = new PdfPCell();
		// Setting the background color and padding of the table cell
		cell.setBackgroundColor(CMYKColor.BLUE);
		cell.setPadding(5);
		// Creating font
		// Setting font style and size
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.WHITE);
		// Adding headings in the created table cell or header
		// Adding Cell to table
		cell.setPhrase(new Phrase("ID", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Citizen Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Plan Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Plan Status", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Gender", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Plan Start Date", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Plan End Date", font));
		table.addCell(cell);

//		table.addCell("ID");
//		table.addCell("Citizen Name");
//		table.addCell("Plan Name");
//		table.addCell("Plan Status");
//		table.addCell("Gender");
//		table.addCell("Plan Start Date");
//		table.addCell("Plan End Date");
//		
		 

		for (CitezenPlan plan : plans) {
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			table.addCell(plan.getGender());
			table.addCell(plan.getPlanStartDate() + "");
			table.addCell(plan.getPlanEndDate() + "");
		}

		document.add(table);
		document.close();
	}
}
