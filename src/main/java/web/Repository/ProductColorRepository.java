
package web.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.Model.ProductColor;
import web.Model.ProductColorPK;

@Repository
public interface ProductColorRepository extends CrudRepository<ProductColor, ProductColorPK>{
    
}
