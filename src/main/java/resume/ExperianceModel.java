package resume;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExperianceModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String company;
    private String position;
    private String startingYear;
    private String endYear;
    private String duty;
    private String username;
   
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getStartingYear() {
		return startingYear;
	}
	public void setStartingYear(String startingYear) {
		this.startingYear = startingYear;
	}
	
	public String getEndYear() {
		return endYear;
	}
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	    
    

}
