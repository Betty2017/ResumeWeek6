package resume;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ExperianceRepository extends CrudRepository<ExperianceModel, Integer>{
	List<ExperianceModel> findByUsername(String username);
}

