package net.etfbl.facultyapp.dto;

public class StudentDTO {
	
	    private Integer id;
	    private String firstName;
	    private String lastName;
	    private String indexNumber;
	    private int yearOfStudy;
	    
	    private String username;
	    private String password;
	    
	    public StudentDTO() {}

	    public StudentDTO(String f, String l, String i, int y, String u) {
	        this.firstName = f;
	        this.lastName = l;
	        this.indexNumber = i;
	        this.yearOfStudy = y;
	        this.username = u;
	    }

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getIndexNumber() {
			return indexNumber;
		}

		public void setIndexNumber(String indexNumber) {
			this.indexNumber = indexNumber;
		}

		public int getYearOfStudy() {
			return yearOfStudy;
		}

		public void setYearOfStudy(int yearOfStudy) {
			this.yearOfStudy = yearOfStudy;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
	    
	    
	
}
