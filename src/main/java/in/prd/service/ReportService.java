package in.prd.service;

import java.util.List;

import in.prd.entity.CitezenPlan;
import in.prd.request.SearchRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {

	public List<String> getPlanNames();
	
	public List<String> getPlanStatus();
	
	public List<CitezenPlan> search(SearchRequest request);
	
	public boolean exportExcel(HttpServletResponse response) throws Exception;
	
	public boolean exportPdf (HttpServletResponse response) throws Exception;	
	
	
}
