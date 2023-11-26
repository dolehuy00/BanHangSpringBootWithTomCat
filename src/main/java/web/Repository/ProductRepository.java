
package web.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.Model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
    
    @Query("FROM Product p ORDER BY p.createAt DESC LIMIT :limit")
    public List<Product> getProductNewest(@Param("limit") Integer limit);
    
}
