
package web.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.Model.Customer;
import web.Model.Orders;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Integer>{
    
    @Query("FROM Orders o WHERE o.customerID = :customer")
    public List<Orders> findOrdersByCustomer(@Param("customer") Customer customer);
}
