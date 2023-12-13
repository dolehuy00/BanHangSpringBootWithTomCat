
package web.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.Model.Color;

@Repository
public interface ColorRepository extends CrudRepository<Color, Integer>{
    
    @Query("FROM Color c WHERE c.name LIKE ?1"
            + " OR c.colorID LIKE ?1")
    public List<Color> searchInManage(String keyword);
}
