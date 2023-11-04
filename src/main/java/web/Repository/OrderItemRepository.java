
package web.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.Model.OrderItem;
import web.Model.OrderItemPK;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, OrderItemPK>{
    
}
