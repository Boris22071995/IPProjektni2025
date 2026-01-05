package net.etfbl.facultyapp.dto;

import java.time.LocalDate;
import java.util.List;

public class InternshipDTO {
    
	 private Integer id;
	    private String title;
	    private String companyName;

	    private String description;
	    private String requirements;
	    private String restriction;

	    private String startDate;
	    private String endDate;

	    private List<String> technologies;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getRequirements() {
			return requirements;
		}

		public void setRequirements(String requirements) {
			this.requirements = requirements;
		}

		public String getRestriction() {
			return restriction;
		}

		public void setRestriction(String restriction) {
			this.restriction = restriction;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public List<String> getTechnologies() {
			return technologies;
		}

		public void setTechnologies(List<String> technologies) {
			this.technologies = technologies;
		}
	    
	    
	
}
