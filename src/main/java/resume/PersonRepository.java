package resume;


import resume.PersonModel;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonModel, Integer>{
	
}