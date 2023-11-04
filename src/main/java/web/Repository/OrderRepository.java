
package web.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.Model.Orders;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Integer>{
    
}
