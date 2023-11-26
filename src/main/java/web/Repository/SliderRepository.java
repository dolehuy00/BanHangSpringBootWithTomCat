
package web.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.Model.Slider;

@Repository
public interface SliderRepository extends CrudRepository<Slider, Integer>{
    
    
}
