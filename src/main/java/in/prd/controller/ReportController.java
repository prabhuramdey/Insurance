package in.prd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.prd.entity.CitezenPlan;
import in.prd.request.SearchRequest;
import in.prd.service.ReportServiceImpl;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController {

	@Autowired
	private ReportServiceImpl service;

	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse response) throws Exception{
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment;filename=plans.pdf");
		service.exportPdf(response);
		
	}
	
	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response) throws Exception{
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;filename=plans.xls");
		service.exportExcel(response);
		
	}
	
	
	@PostMapping("/searchData")
	public String handleSearch(@ModelAttribute("searchData") SearchRequest request, Model model) {
		System.out.println(request);
		List<CitezenPlan> plans = service.search(request);
		model.addAttribute("plans", plans);
		init(model);
		return "index";
	}

	@GetMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("search", new SearchRequest());
		init(model);
		return "index";
	}

	private void init(Model model) {
		//model.addAttribute("search", new SearchRequest());
		model.addAttribute("names", service.getPlanNames());
		model.addAttribute("status", service.getPlanStatus());
	}
}
