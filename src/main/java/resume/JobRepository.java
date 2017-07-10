package resume;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<JobModel, Integer>{
	List<JobModel> findByUsername(String username);
	
}