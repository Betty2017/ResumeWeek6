package resume;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername (String username);
	
	User findByEmail(String email);

    Long countByEmail(String email);

    Long countByUsername(String username);
    
    List<User> findByFirstName(String firstName);

   
}
