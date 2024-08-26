package in.prd.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import in.prd.entity.CitezenPlan;
import in.prd.repository.CitezenPlanRepository;
import in.prd.request.SearchRequest;
import in.prd.util.EmailUtils;
import in.prd.util.ExcelGenerator;
import in.prd.util.PdfGenerator;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitezenPlanRepository planRepo;

	@Autowired
	private ExcelGenerator excelGenerator;

	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public List<String> getPlanNames() {
		return planRepo.getPlanNames();

	}

	@Override
	public List<String> getPlanStatus() {
		return planRepo.getPlanStatus();
	}

	@Override
	public List<CitezenPlan> search(SearchRequest request) {
		CitezenPlan entity = new CitezenPlan();

		if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}

		if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}

		if (null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}

		if (null != request.getStartDate() && !"".equals(request.getStartDate())) {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(localDate);
		}

		if (null != request.getEndDate() && !"".equals(request.getEndDate())) {
			String endDate = request.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(endDate, formatter);
			entity.setPlanEndDate(localDate);
		}

		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		List<CitezenPlan> plans = planRepo.findAll();
		File f=new File("Plans.xls");
		excelGenerator.generate(response, plans,f);
		String subject="Test mail subject";
		String body="<h1> Test mail Body </h1>";
		String to="chinudey2000@gmail.com";
		emailUtils.sendEmail(subject, body, to, f);
		f.delete();
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		
		File f=new File("Plans.pdf");
		List<CitezenPlan> plans = planRepo.findAll();
		pdfGenerator.generate(response, plans,f);
		String subject="Test mail subject";
		String body="<h1> Test mail Body </h1>";
		String to="chinudey2000@gmail.com";
		emailUtils.sendEmail(subject, body, to, f);
		f.delete();
		
		return true;
	}

}
