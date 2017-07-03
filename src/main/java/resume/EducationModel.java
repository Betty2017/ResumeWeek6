package resume;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EducationTable")
public class EducationModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer eduId;
	private String degree; 
	private String institution; 
	private String year;

	
	public Integer getEduId() {
		return eduId;
	}
	public void setEduId(Integer eduId) {
		this.eduId = eduId;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public EducationModel(){
		
	}
	public void addEdutoDb(Integer personID)
	{
	  
		
	}
}
