package resume;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EducationRepository extends CrudRepository<EducationModel, Integer>{
	List<EducationModel> findByUsername(String username);
}