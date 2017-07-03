package resume;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<SkillsModel, Integer>{
	List<SkillsModel> findByUsername(String username);
}
	
	
