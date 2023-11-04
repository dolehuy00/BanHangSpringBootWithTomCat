
package web.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.Model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
    
}
