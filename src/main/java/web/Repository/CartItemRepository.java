
package web.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.Model.Cartitem;
import web.Model.CartitemPK;

@Repository
public interface CartItemRepository extends CrudRepository<Cartitem, CartitemPK>{
    
}
