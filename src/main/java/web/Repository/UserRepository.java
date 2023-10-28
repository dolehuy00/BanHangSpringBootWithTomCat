
package web.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.Model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    
}
