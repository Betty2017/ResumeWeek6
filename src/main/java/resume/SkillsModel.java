package resume;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SkillsModel {
	
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer id;
private String skills;
private String rating;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getSkills() {
	return skills;
}
public void setSkills(String skills) {
	this.skills = skills;
}
public String getRating() {
	return rating;
}
public void setRating(String rating) {
	this.rating = rating;
}




}
