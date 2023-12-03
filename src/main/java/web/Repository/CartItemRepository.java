
package web.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.Model.Cartitem;
import web.Model.CartitemPK;

@Repository
public interface CartItemRepository extends CrudRepository<Cartitem, CartitemPK>{
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Cartitem c WHERE c.cartitemPK = ?1")
    public Integer deleteCartItem(CartitemPK id);
}
