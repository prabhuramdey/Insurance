package in.prd.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.prd.entity.CitezenPlan;
import in.prd.repository.CitezenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CitezenPlanRepository repository;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		
		repository.deleteAll();
		
		
		// cash plan data
		CitezenPlan c1 = new CitezenPlan();
		c1.setCitizenName("Prabhu");
		c1.setGender("Male");
		c1.setPlanName("cash");
		c1.setPlanStatus("Approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenifitAmt(50000.00);

		CitezenPlan c2 = new CitezenPlan();
		c2.setCitizenName("Trupti");
		c2.setGender("Female");
		c2.setPlanName("cash");
		c2.setPlanStatus("Denied");
		c2.setDenialReason("Rental Income");

		CitezenPlan c3 = new CitezenPlan();
		c3.setCitizenName("Akash");
		c3.setGender("Male");
		c3.setPlanName("cash");
		c3.setPlanStatus("Terminated");
		c3.setPlanStartDate(LocalDate.now().minusMonths(3));
		c3.setPlanEndDate(LocalDate.now().plusMonths(3));
		c3.setBenifitAmt(25000.00);
		c3.setTerminatedDate(LocalDate.now());
		c3.setTerminationRsn("Employed");

		// food plan data
		CitezenPlan c4 = new CitezenPlan();
		c4.setCitizenName("Puja");
		c4.setGender("Female");
		c4.setPlanName("food");
		c4.setPlanStatus("Approved");
		c4.setPlanStartDate(LocalDate.now());
		c4.setPlanEndDate(LocalDate.now().plusMonths(6));
		c4.setBenifitAmt(8000.00);

		CitezenPlan c5 = new CitezenPlan();
		c5.setCitizenName("Jyoti");
		c5.setGender("Male");
		c5.setPlanName("food");
		c5.setPlanStatus("Denied");
		c5.setDenialReason("Property Income");

		CitezenPlan c6 = new CitezenPlan();
		c6.setCitizenName("Somu");
		c6.setGender("Male");
		c6.setPlanName("food");
		c6.setPlanStatus("Terminated");
		c6.setPlanStartDate(LocalDate.now().minusMonths(1));
		c6.setPlanEndDate(LocalDate.now().plusMonths(5));
		c6.setBenifitAmt(30000.00);
		c6.setTerminatedDate(LocalDate.now());
		c6.setTerminationRsn("Employed");

		// medical plan data
		CitezenPlan c7 = new CitezenPlan();
		c7.setCitizenName("Simran");
		c7.setGender("Female");
		c7.setPlanName("medical");
		c7.setPlanStatus("Approved");
		c7.setPlanStartDate(LocalDate.now());
		c7.setPlanEndDate(LocalDate.now().plusMonths(6));
		c7.setBenifitAmt(10000.00);

		CitezenPlan c8 = new CitezenPlan();
		c8.setCitizenName("Alok");
		c8.setGender("Male");
		c8.setPlanName("medical");
		c8.setPlanStatus("Denied");
		c8.setDenialReason("Property Income");

		CitezenPlan c9 = new CitezenPlan();
		c9.setCitizenName("Ananya");
		c9.setGender("Female");
		c9.setPlanName("medical");
		c9.setPlanStatus("Terminated");
		c9.setPlanStartDate(LocalDate.now().minusMonths(1));
		c9.setPlanEndDate(LocalDate.now().plusMonths(5));
		c9.setBenifitAmt(50000.00);
		c9.setTerminatedDate(LocalDate.now());
		c9.setTerminationRsn("Govt Job");

		// employment plan data
		CitezenPlan c10 = new CitezenPlan();
		c10.setCitizenName("Kaushick");
		c10.setGender("Male");
		c10.setPlanName("employment");
		c10.setPlanStatus("Approved");
		c10.setPlanStartDate(LocalDate.now());
		c10.setPlanEndDate(LocalDate.now().plusMonths(6));
		c10.setBenifitAmt(10000.00);

		CitezenPlan c11 = new CitezenPlan();
		c11.setCitizenName("Swagatika");
		c11.setGender("Female");
		c11.setPlanName("employment");
		c11.setPlanStatus("Denied");
		c11.setDenialReason("Property Income");

		CitezenPlan c12 = new CitezenPlan();
		c12.setCitizenName("Laltu");
		c12.setGender("Male");
		c12.setPlanName("employment");
		c12.setPlanStatus("Terminated");
		c12.setPlanStartDate(LocalDate.now().minusMonths(3));
		c12.setPlanEndDate(LocalDate.now().plusMonths(3));
		c12.setBenifitAmt(20000.00);
		c12.setTerminatedDate(LocalDate.now());
		c12.setTerminationRsn("Private Job");
		
		
		List<CitezenPlan> list = Arrays.asList(c1,c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12);
		repository.saveAll(list);

	}

}
