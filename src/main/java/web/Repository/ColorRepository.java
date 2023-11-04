
package web.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.Model.Color;

@Repository
public interface ColorRepository extends CrudRepository<Color, Integer>{
    
}
