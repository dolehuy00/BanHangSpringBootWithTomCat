
package web.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.Model.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status, Integer>{
    
}
