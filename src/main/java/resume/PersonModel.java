package resume;

import java.util.ArrayList;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="personTable")
public class PersonModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer personid; 
	private String fname;
	private String lname;
	private String email;
	private String username;
	
	
	private ArrayList <JobModel> myjoblist; 
	private ArrayList <EducationModel> myedulist; 
	private ArrayList <SkillsModel> myskilllist;
	
	public PersonModel() {	
		
		myjoblist = new ArrayList <JobModel>(); 
		myedulist = new ArrayList<EducationModel>(); 
		myskilllist = new ArrayList<SkillsModel>();
	}
	
	public PersonModel(String pfname, String plname, String pemail) {	
		this.fname = pfname; 
		this.lname = plname; 
		this.email = pemail; 
		myjoblist = new ArrayList <JobModel>(); 
		myedulist = new ArrayList<EducationModel>(); 
		myskilllist = new ArrayList<SkillsModel>();
	}
	
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getPersonid() {
		return personid;
	}

	public void setPersonid(Integer personid) {
		this.personid = personid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void addEdutoDb(EducationModel edutoadd){
		myedulist.add(edutoadd);
		
	}
	
	public void listMyEdus(){

		for(EducationModel edu : myedulist){
			
			System.out.println(edu.getEduId());
			System.out.println(edu.getDegree());
			System.out.println(edu.getInstitution());
			System.out.println(edu.getYear());
			}
		}
		
	}
	
	
	

