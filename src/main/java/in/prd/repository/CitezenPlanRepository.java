package in.prd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.prd.entity.CitezenPlan;

public interface CitezenPlanRepository extends JpaRepository<CitezenPlan, Integer> {

	@Query("select distinct c.planName from CitezenPlan c")
	public List<String> getPlanNames();

	@Query("select distinct c.planStatus from CitezenPlan c")
	public List<String> getPlanStatus();

}
