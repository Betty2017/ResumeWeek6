package resume;


import resume.PersonModel;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonModel, Integer>{
	List<PersonModel> findByUsername(String username);
	List<PersonModel> findByfname(String fname);
	List<PersonModel> findBylname(String lname);
}