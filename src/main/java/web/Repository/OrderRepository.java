
package web.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.Model.Customer;
import web.Model.Orders;
import web.Model.User;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Integer>{
    
    @Query("FROM Orders o WHERE o.customerID = :customer")
    public List<Orders> findOrdersByCustomer(@Param("customer") Customer customer); 
     
    @Query("FROM Orders o WHERE o.status.orderStatusId = 1")
    public List<Orders> findOrdersAwaiting(); 
    
    @Query("FROM Orders o WHERE o.seller = :user")
    public List<Orders> findOrdersByUser(@Param("user") User user);
     
    @Query("FROM Orders o WHERE o.status.orderStatusId = 2 AND o.seller.userID = ?1")
    public List<Orders> findOrdersConfirmed(Integer userId); 
    
    @Query("FROM Orders o WHERE o.status.orderStatusId = 3 AND o.seller.userID = ?1")
    public List<Orders> findOrdersDelivering(Integer userId);
    
    @Query("FROM Orders o WHERE o.status.orderStatusId = 4 OR o.status.orderStatusId = 5 AND o.seller.userID = ?1")
    public List<Orders> findOrdersFinished(Integer userId);
    
    @Query("FROM Orders o WHERE o.customerID.name LIKE ?1"
            + " OR o.customerID.customerID LIKE ?1"
            + " OR o.seller.userID LIKE ?1"
            + " OR o.seller.name LIKE ?1"
            + " OR o.phoneNumber LIKE ?1"
            + " OR o.status.name LIKE ?1")
    public List<Orders> searchInManage(String keyword);
}
