
package web.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.Model.Cart;
import web.Model.Customer;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer>, JpaRepository<Cart, Integer>{
    
    public Cart findByCustomer(Customer customer);
    
    @Query("FROM Cart c WHERE c.customer.name LIKE ?1"
            + " OR c.customer.customerID LIKE ?1")
    public List<Cart> searchInManage(String keyword);
}
