package resume;

import resume.EducationModel;
import org.springframework.data.repository.CrudRepository;

public interface EducationRepository extends CrudRepository<EducationModel, Integer>{
}