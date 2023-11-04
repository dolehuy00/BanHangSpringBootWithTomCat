
package web.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.Model.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer>{
    
}
